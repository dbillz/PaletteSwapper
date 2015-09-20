package com.dan.paleteSwapper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Palette {

	private final List<Color> colorList;
	
	/**
	 * Pass this the output from ImageLoader's GetColorsInImage method and the number of colors that you want
	 * @param colorCountMap
	 * @param paletteSize
	 */
	public Palette(Map<Color,Integer> colorCountMap, int paletteSize, Tolerance simplifyTolerance){
		
		if(simplifyTolerance.getFilterLimit()>0){
			colorCountMap = simplifyPalette(colorCountMap,simplifyTolerance);
		}
		
		
		colorList = new LinkedList<Color>(); //using LL to maintain insertion order
		List<Entry<Color,Integer>> entryList = new ArrayList<Entry<Color,Integer>>(colorCountMap.entrySet());
		Collections.sort(entryList,new Comparator<Entry<Color,Integer>>(){
			public int compare(Entry<Color,Integer> o1, Entry<Color,Integer> o2){
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		for(int i = 0; i < Math.min(paletteSize,colorCountMap.keySet().size()); i++){
			colorList.add(entryList.get(i).getKey());
		}
	}
	
	/**
	 * 
	 * @param inputString - string of rgb values, in the format "rrr ggg bbb,rrr ggg bbb,rr gg bb,r g b"
	 */
	public Palette(String inputString){
		colorList = new LinkedList<Color>();
		String[] subStrings = inputString.split(",");
		for(String s:subStrings){
			String[] rgbArray = s.split(" ");
			Color newColor = new Color(Integer.valueOf(rgbArray[0]),Integer.valueOf(rgbArray[1]),Integer.valueOf(rgbArray[2]));
			colorList.add(newColor);
		}
	}
	
	/**
	 * Removes all duplicates which are within the tolerance integer from a more common color in the palette.
	 * @param tolerance
	 */
	private Map<Color,Integer> simplifyPalette(Map<Color,Integer> colorCountMap,Tolerance tolerance){
		
		Map<Color,Integer> simplifiedMap = new HashMap<Color,Integer>();
		
		while(!colorCountMap.isEmpty()){
			Iterator it = colorCountMap.entrySet().iterator();
			Color dominantColor = null;
			int dominantCount = 0;
			int combinedCount = 0;
			while(it.hasNext()){
				Entry<Color,Integer> pair = (Entry<Color,Integer>)it.next();
				if(dominantColor==null){
					dominantColor = pair.getKey();
					dominantCount = pair.getValue();
					combinedCount = pair.getValue();
					it.remove();
				}
				else{
					if(getColorDifference(dominantColor,pair.getKey())<tolerance.getFilterLimit()){
						combinedCount += pair.getValue();
						if(pair.getValue() > dominantCount){
							dominantColor = pair.getKey();
							dominantCount = pair.getValue();
						}
						it.remove();
					}
				}
				simplifiedMap.put(dominantColor, combinedCount);
			}
		}
		return simplifiedMap;
		
	}
	
	private int getColorDifference(Color c1, Color c2){
		return (Math.abs(c1.getRGB()-c2.getRGB()));
	}
	
	public List<Color> getColorList(){
		return colorList;
	}
	
	public void printColorList(){
		for(Color c: colorList){
			System.out.println(c.getRed()+ " "+c.getGreen()+" " + c.getBlue());
		}
	}
	
	public String getPaletteString(){
		StringBuilder sb = new StringBuilder();
		if(!colorList.isEmpty()){
			for(Color c:colorList){
				sb.append(c.getRed());
				sb.append(" ");
				sb.append(c.getGreen());
				sb.append(" ");
				sb.append(c.getBlue());
				sb.append(",");
			}
		}
		return sb.toString();
		
	}
}
