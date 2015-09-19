package com.dan.paleteSwapper;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageLoader {

	public Map<Color,Integer> getColorsInImage(String filename){
		Map<Color,Integer> colorMap = new HashMap<Color,Integer>();
		try {
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
		}
		return colorMap;
	}
	
	public void createPaletteImage(Palette p, String filename){
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
		try {
			ImageIO.write(outputImage,"PNG", new File(filename));
		} catch (IOException e) {
			System.err.println("Failed to write image");
		}
	}
}
