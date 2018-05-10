import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;

import imagereader.IImageIO;


class ImplementImageIO implements IImageIO{

	/* read a bmp without java Api*/
	public Image myRead(String filepath) throws IOException {
		try {
			FileInputStream bmpStream = new FileInputStream(filepath);
			//读取时丢掉前面的18位，  
	        //读取图片的宽度18~21
			bmpStream.skip(18);
			byte[] widthByte = new byte[4];
			bmpStream.read(widthByte);
			//读取图片的高度22~25
			byte[] heightByte = new byte[4];
			bmpStream.read(heightByte);
			
			//得到图片的真实的width和height
			int width = byteToInt(widthByte);
			int height = byteToInt(heightByte);
			
			//store pixel
			int[] pixel = new int[width * height];
			
			//读取位图数据，位图中数据时从54位开始的，在读取数据前要丢掉前面的数据  54-18-4-4=28 
			bmpStream.skip(28);
			
			int skipNum = (4 - width * 3 % 4) % 4;
			
			for(int i = height - 1; i >= 0; --i) {
				for(int j = 0; j < width; ++j) {
					int blue = bmpStream.read() & 0xff;
					int green = bmpStream.read() & 0xff;
					int red = bmpStream.read() & 0xff;
					
					//创建一个Color对象
					Color color = new Color(red, green, blue);
					pixel[i * width + j] = color.getRGB();
				}
				bmpStream.skip(skipNum);
			}
			bmpStream.close();
			return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, pixel, 0, width));
			
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	//byte to int 
	public int byteToInt(byte[] byteArray) {
		int a = byteArray[3] & 0xff;
		int b = byteArray[2] & 0xff;
		int c = byteArray[1] & 0xff;
		int d = byteArray[0] & 0xff;
		int num = a << 24 | b << 16 | c << 8 | d;
		return num;
	}
	
	@Override
	public Image myWrite(Image img, String filepath) throws IOException {
		try {
			File outFile = new File(filepath + ".bmp");
			// Create a buffered image with transparency
			/*
			 * 	TYPE_INT_ARGB can't save successful(创建一个带透明色的BufferedImage对象)
			 * 	TYPE_INT_RGB can save successful(创建一个不带透明色的BufferedImage对象)
			 * */
			BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
			
			// Draw the image on to the buffered image
		    Graphics2D bGr = bufferedImage.createGraphics();
		    bGr.drawImage(img, 0, 0, null);
		    bGr.dispose();
			ImageIO.write(bufferedImage, "bmp", outFile);
			return bufferedImage;
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
}