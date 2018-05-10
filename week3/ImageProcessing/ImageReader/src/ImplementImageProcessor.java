import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import imagereader.IImageProcessor;

class ImplementImageProcessor implements IImageProcessor {

	@Override
	public Image showGray(Image image) {
		// TODO Auto-generated method stub
		return colorFilter(image, 3);
	}
	
	@Override
	public Image showChanelB(Image image) {
		// TODO Auto-generated method stub
		return colorFilter(image, 2);
	}

	@Override
	public Image showChanelG(Image image) {
		// TODO Auto-generated method stub
		return colorFilter(image, 1);
	}

	@Override
	public Image showChanelR(Image image) {
		// TODO Auto-generated method stub
		return colorFilter(image, 0);
	}
	
	/*
	 * 	red - 0
	 *	green - 1
	 *	blue - 2
	 *	gray - 3
	 * */
	private Image colorFilter(Image img, int type) {
		BufferedImage newImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		
		// Draw the image on to the buffered image
	    Graphics2D bGr = newImage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();
	    int width = img.getWidth(null);
	    int height = img.getHeight(null);
	    for(int i = 0; i < width; ++i) {
	    	for(int j = 0; j < height; ++j) {
	    		int rgb = newImage.getRGB(i, j);
	    		if(type == 0) {
	    			//red
	    			rgb = 0xffff0000 & rgb;
	    		}
	    		else if(type == 1) {
	    			//green
	    			rgb = 0xff00ff00 & rgb;
	    		}
	    		else if(type == 2) {
	    			//blue
	    			rgb = 0xff0000ff & rgb;
	    		}
	    		else {
	    			//gray
	    			int gray = (int)(((rgb & 0x00ff0000) >> 16) * 0.299 + ((rgb & 0x0000ff00) >> 8) * 0.587 + (rgb & 0x000000ff) * 0.114);
	    			rgb =  (rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray;
	    		}
	    		newImage.setRGB(i, j, rgb);
	    	}
	    }
		return newImage;
	}
	
}