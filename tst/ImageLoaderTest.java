import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dan.paleteSwapper.ImageLoader;
import com.dan.paleteSwapper.Palette;


public class ImageLoaderTest {

	private ImageLoader il;
	
	
	
	@Before
	public void setUp(){
		il = new ImageLoader();
	}
	
	@Test
	public void testGetColorsInImage(){		
		
		//input image for test
		String inputFilename = "tst/testResources/testInput.png";
		//known values about input image
		int nColorsInInput = 44;
		Color white = new Color(255,255,255);
		int nWhiteInInput = 1075;
		Color black = new Color(0,0,0);
		int nBlackInInput = 129;
		
		
		Map<Color,Integer> colorMap = il.getColorsInImage(inputFilename);
		//Check that map has the right number of colors
		Assert.assertEquals(colorMap.size(),nColorsInInput);
		//Check that map stored the right counts for colors
		Assert.assertEquals((int) colorMap.get(white),nWhiteInInput);
		Assert.assertEquals((int) colorMap.get(black),nBlackInInput);
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
		BufferedImage createdImage = il.getImageFromPalette(p);
		Assert.assertEquals(createdImage.getWidth(),expectedWidth);
		Assert.assertEquals(createdImage.getHeight(),expectedHeight);
		int firstColorRGB = createdImage.getRGB(0,0);
		Assert.assertEquals(firstColorRGB,expectedFirstColor.getRGB());
		int lastColorRGB = createdImage.getRGB(expectedWidth-1, expectedHeight-1);
		Assert.assertEquals(lastColorRGB,expectedLastColor.getRGB());
	}
	
	
	
}
