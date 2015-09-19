package com.dan.paleteSwapper;

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
		commandLineInterface();
	}
	
	public static void commandLineInterface(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Input path to source image, which we're copying the palette from:");
		String inputPath = sc.nextLine();
		System.out.println("Input path to output the palette to:");
		String outputPath = sc.nextLine();
		System.out.println("Input tolerance (NONE,VERYLOW,LOW,NORMAL,HIGH,EXTREME):");
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
		System.out.println("Input desired palette size (try 16):");
		int size = sc.nextInt();
		
		ImageLoader il = new ImageLoader();
		Palette p = new Palette(il.getColorsInImage(inputPath),size,t);
		p.printColorList();
		il.createPaletteImage(p, outputPath);
	}
}
