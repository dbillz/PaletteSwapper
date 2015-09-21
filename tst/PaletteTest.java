import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import com.dan.paleteSwapper.Palette;
import com.dan.paleteSwapper.Tolerance;


public class PaletteTest {
	

	@Test
	public void PaletteConstructorTest(){

		Map<Color,Integer> testInputMap = new HashMap<Color,Integer>();
		testInputMap.put(Color.WHITE,100);
		testInputMap.put(Color.BLUE,200);
		testInputMap.put(Color.BLACK, 300);
		int testPaletteSize = 3;
		Tolerance testTolerance = Tolerance.ZERO;
		
		Palette p = new Palette(testInputMap,testPaletteSize,testTolerance);
		
		Assert.assertEquals(p.getColorList().size(),3);
		Assert.assertEquals(p.getColorList().get(0),Color.BLACK);
		Assert.assertEquals(p.getColorList().get(1),Color.BLUE);
		Assert.assertEquals(p.getColorList().get(2),Color.WHITE);
	}
	
	@Test
	public void PaletteConstructorWithHigherPaletteSize(){
		Map<Color,Integer> testInputMap = new HashMap<Color,Integer>();
		testInputMap.put(Color.WHITE,100);
		testInputMap.put(Color.BLUE,200);
		testInputMap.put(Color.BLACK, 300);
		int testPaletteSize = 999;
		Tolerance testTolerance = Tolerance.ZERO;
		
		Palette p = new Palette(testInputMap,testPaletteSize,testTolerance);
		
		Assert.assertEquals(p.getColorList().size(),3);
		Assert.assertEquals(p.getColorList().get(0),Color.BLACK);
		Assert.assertEquals(p.getColorList().get(1),Color.BLUE);
		Assert.assertEquals(p.getColorList().get(2),Color.WHITE);
	}
	
	@Test
	public void PaletteConstructorWithLowerPaletteSize(){
		Map<Color,Integer> testInputMap = new HashMap<Color,Integer>();
		testInputMap.put(Color.WHITE,100);
		testInputMap.put(Color.BLUE,200);
		testInputMap.put(Color.BLACK, 300);
		int testPaletteSize = 1;
		Tolerance testTolerance = Tolerance.ZERO;
		
		Palette p = new Palette(testInputMap,testPaletteSize,testTolerance);
		
		Assert.assertEquals(p.getColorList().size(),1);
		Assert.assertEquals(p.getColorList().get(0),Color.BLACK);
	}
	
	@Test
	public void PaletteStringConstructorTest(){
		String inputString = "0 0 0,255 255 255,128 128 128";
		Color testColor = new Color(128,128,128);
		Palette p = new Palette(inputString);
		
		Assert.assertEquals(p.getColorList().size(),3);
		Assert.assertEquals(p.getColorList().get(0),Color.BLACK);
		Assert.assertEquals(p.getColorList().get(1),Color.WHITE);
		Assert.assertEquals(p.getColorList().get(2),testColor);
	}
	
	@Test
	public void getPaletteStringTest(){
		//Note that I'm constructing this with a string, but the object doesn't save the input string
		//This is re-creating the input string and testing that it creates the proper thing.
		String inputString = "0 0 0,255 255 255,128 128 128,";
		Palette p = new Palette(inputString);
		Assert.assertEquals(p.getPaletteString(),inputString);
	}
	
	@Test
	public void testGetImageFromPalette(){
		
		int pixelSize = 32;
		int expectedWidth = 5*pixelSize;
		int expectedHeight = 1*pixelSize;
		Color expectedFirstColor = new Color(0,0,0);
		Color expectedLastColor = new Color(4,4,4);
		
		String inputPalette = "0 0 0,1 1 1,2 2 2,3 3 3,4 4 4";
		Palette p = new Palette(inputPalette);
		BufferedImage createdImage = p.getImageFromPalette();
		Assert.assertEquals(createdImage.getWidth(),expectedWidth);
		Assert.assertEquals(createdImage.getHeight(),expectedHeight);
		int firstColorRGB = createdImage.getRGB(0,0);
		Assert.assertEquals(firstColorRGB,expectedFirstColor.getRGB());
		int lastColorRGB = createdImage.getRGB(expectedWidth-1, expectedHeight-1);
		Assert.assertEquals(lastColorRGB,expectedLastColor.getRGB());
	}
	
}
