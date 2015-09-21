package com.dan.paleteSwapper;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Painter {

	
	BufferedImage applyPaletteToImage(BufferedImage inputImage, Palette p){
		//For each pixel in the image,
		//Find the closest palette color and apply it
		BufferedImage outputImage = new BufferedImage(inputImage.getWidth(),inputImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
		
		List<Integer> paletteRGBList = getRGBListFromPalette(p);
		
		
		for(int i = 0; i < inputImage.getHeight(); i++){
			for(int j = 0; j < inputImage.getWidth(); j++){
				int closestRGBColor = searchListForClosest(inputImage.getRGB(j, i),paletteRGBList);
				outputImage.setRGB(j,i,closestRGBColor);
			}
		}
		
		return outputImage;
	}
	

	private int searchListForClosest(int inputValue, List<Integer> intList){
		
		int closest = inputValue;
		int closestDiff = Integer.MAX_VALUE;
		
		for(int thisInt:intList){
			final int thisDiff = Math.abs(thisInt-inputValue);
			if(thisDiff<closestDiff){
				closest=thisInt;
				closestDiff=thisDiff;
			}
			if(thisInt>inputValue)	break; //Break once we're past the value in the sorted list
		}
		
		return closest;
	}
	
	//This method is split out to save it and optimize the method above it.
	private List<Integer> getRGBListFromPalette(Palette p){
		List<Integer> rgbList = new ArrayList<Integer>();
		for(Color c:p.getColorList()){
			rgbList.add(c.getRGB());
		}
		Collections.sort(rgbList);
		return rgbList;
	}
	
	
}
