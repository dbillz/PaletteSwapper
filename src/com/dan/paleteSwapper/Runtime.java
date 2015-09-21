package com.dan.paleteSwapper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

/**
 * This program takes an image and creates an image of its primary colors.
 * 
 * I plan to expand this to then take the palette and apply it back to another image, to facilitate
 * easy re-using of video game sprites.
 * 
 * @author Dan
 *
 */
public class Runtime {

	public static void main(String[] args){
		cliSwapPalettes();
	}
	
	
	public static void cliSwapPalettes(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Input path to source image, which we're copying the colors from");
		String inputPath = sc.nextLine();
		System.out.println("Input path the image which we're copying the colors to");
		String inputPath2 = sc.nextLine();
		System.out.println("Input output path for the new image.");
		String outputPath = sc.nextLine();
		System.out.println("Input output path for the palette image");
		String outputPath2 = sc.nextLine();
		System.out.println("Input tolerance (NONE,VERYLOW,LOW,NORMAL,HIGH,EXTREME):\nHigher tolerance merges similar colors.");
		String tolerance = sc.nextLine();
		Tolerance t; 
		switch(tolerance){
			case("NONE"): t= Tolerance.ZERO; break;
			case("VERYLOW"): t=Tolerance.VERY_LOW; break;
			case("LOW"): t=Tolerance.LOW; break;
			case("NORMAL"): t=Tolerance.NORMAL; break;
			case("HIGH"): t=Tolerance.HIGH; break;
			case("EXTREME"): t=Tolerance.EXTREME; break;
			default: t=Tolerance.NORMAL;
		}
		System.out.println("Input desired palette size (higher = more colors):");
		int size = sc.nextInt();
		
		
		ImageLoader il = new ImageLoader();
		try {
			BufferedImage inputImage = il.loadImage(inputPath2);
						
			Palette p = new Palette(il.getColorsInImage(inputPath),size,t);
			
			Painter painter = new Painter();
			BufferedImage newImage = painter.applyPaletteToImage(inputImage, p);
			
			il.saveImage(newImage, outputPath);
			
			il.saveImage(p.getImageFromPalette(),outputPath2);
			
			
		} catch (IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thanks for running me!");
		
	}
}
