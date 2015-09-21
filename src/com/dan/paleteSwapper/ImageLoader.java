package com.dan.paleteSwapper;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import com.google.common.base.Preconditions;

public class ImageLoader {

	public Map<Color,Integer> getColorsInImage(String filename){
		
		
		Map<Color,Integer> colorMap = new HashMap<Color,Integer>();
		try {
			Preconditions.checkNotNull(filename);
			
			BufferedImage image = ImageIO.read(new File(filename));
			for (int y = 0; y < image.getHeight(); y++) {
			    for (int x = 0; x < image.getWidth(); x++) {
			    	Color c = new Color(image.getRGB(x, y));
			    	if(colorMap.get(c)!=null){
			    		int currentCount = colorMap.get(c);
			    		colorMap.put(c,currentCount+1);
			    	}
			    	else{
			    		colorMap.put(c, 1);
			    	}
			    }
			}
		} catch (IOException e) {
			System.err.println("Could not open image " + filename);
		}catch(IllegalArgumentException e){
			if(filename==null)throw new IllegalArgumentException("Filename passed to ImageLoader.getColorsInImage cannot be null");
		}
		return colorMap;
	}
	
	
	public BufferedImage getImageFromPalette(Palette p){
		Preconditions.checkNotNull(p);
		
		List<Color> colorList = p.getColorList();
		int pixelSize = 32;
		int width = pixelSize*colorList.size();
		int height = pixelSize;
		BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				outputImage.setRGB(j, i, colorList.get((int) Math.floor(j/32)).getRGB());
			}
		}
		return outputImage;
	}
	
	public void saveImage(BufferedImage inputImage, String filename){
		Preconditions.checkNotNull(inputImage);
		Preconditions.checkNotNull(filename);
		
		try {
			ImageIO.write(inputImage,"PNG", new File(filename));
		} catch (IOException e) {
			System.err.println("Failed to write image");
		}
	}
}
