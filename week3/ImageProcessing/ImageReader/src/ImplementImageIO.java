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
			//��ȡʱ����ǰ���18λ��  
	        //��ȡͼƬ�Ŀ��18~21
			bmpStream.skip(18);
			byte[] widthByte = new byte[4];
			bmpStream.read(widthByte);
			//��ȡͼƬ�ĸ߶�22~25
			byte[] heightByte = new byte[4];
			bmpStream.read(heightByte);
			
			//�õ�ͼƬ����ʵ��width��height
			int width = byteToInt(widthByte);
			int height = byteToInt(heightByte);
			
			//store pixel
			int[] pixel = new int[width * height];
			
			//��ȡλͼ���ݣ�λͼ������ʱ��54λ��ʼ�ģ��ڶ�ȡ����ǰҪ����ǰ�������  54-18-4-4=28 
			bmpStream.skip(28);
			
			int skipNum = (4 - width * 3 % 4) % 4;
			
			for(int i = height - 1; i >= 0; --i) {
				for(int j = 0; j < width; ++j) {
					int blue = bmpStream.read() & 0xff;
					int green = bmpStream.read() & 0xff;
					int red = bmpStream.read() & 0xff;
					
					//����һ��Color����
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
			 * 	TYPE_INT_ARGB can't save successful(����һ����͸��ɫ��BufferedImage����)
			 * 	TYPE_INT_RGB can save successful(����һ������͸��ɫ��BufferedImage����)
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