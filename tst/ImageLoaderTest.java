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
	
	
	
	
}
