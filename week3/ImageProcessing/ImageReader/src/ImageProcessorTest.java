import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ImageProcessorTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@Test
	public void Test() throws IOException {
		ImageTest("1");
		ImageTest("2");
	}
	
	private void ImageTest(String imageName) throws IOException {
		ImplementImageIO myImageIO = new ImplementImageIO();
		//orign image
		Image orign = myImageIO.myRead("./bmptest/" + imageName + ".bmp");
		Image rImage = myImageIO.myRead("./bmptest/goal/" + imageName + "_red_goal.bmp");
		Image gImage = myImageIO.myRead("./bmptest/goal/" + imageName + "_green_goal.bmp");
		Image bImage = myImageIO.myRead("./bmptest/goal/" + imageName + "_blue_goal.bmp");
		Image grayImage = myImageIO.myRead("./bmptest/goal/" + imageName + "_gray_goal.bmp");
		
		//create image
		ImplementImageProcessor myImageProcessor = new ImplementImageProcessor();
		Image rNowImage = myImageProcessor.showChanelR(orign);
		Image gNowImage = myImageProcessor.showChanelG(orign);
		Image bNowImage = myImageProcessor.showChanelB(orign);
		Image grayNowImage = myImageProcessor.showGray(orign);
		
		//test chanelR
		assertEquals(true, compareImage(rImage, rNowImage));
		//test chanelG
		assertEquals(true, compareImage(gImage, gNowImage));
		//test chanelB
		assertEquals(true, compareImage(bImage, bNowImage));
		//test gray
		assertEquals(true, compareImage(grayImage, grayNowImage));
	}
	
	private boolean compareImage(Image orign, Image now) {
		if(orign.getWidth(null) != now.getWidth(null) || orign.getHeight(null) != now.getHeight(null)) {
			return false;
		}
		BufferedImage orignBufferedImage = new BufferedImage(orign.getWidth(null), orign.getHeight(null), BufferedImage.TYPE_INT_RGB);
		
		// Draw the image on to the buffered image
	    Graphics2D oBGr = orignBufferedImage.createGraphics();
	    oBGr.drawImage(orign, 0, 0, null);
	    oBGr.dispose();
	    
	    BufferedImage nowBufferedImage = new BufferedImage(orign.getWidth(null), orign.getHeight(null), BufferedImage.TYPE_INT_RGB);
	    
	    // Draw the image on to the buffered image
	    Graphics2D nBGr = nowBufferedImage.createGraphics();
	    nBGr.drawImage(orign, 0, 0, null);
	    nBGr.dispose();
	    
	    //compare pixel value
	    for(int i = 0; i < orign.getWidth(null); ++i) {
	    	for(int j = 0; j < orign.getHeight(null); ++j) {
	    		if(orignBufferedImage.getRGB(i, j) != nowBufferedImage.getRGB(i, j)) {
	    			return false;
	    		}
	    	}
	    }
		return true;
	}
}
