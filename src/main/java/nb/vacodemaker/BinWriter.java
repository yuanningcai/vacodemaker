package nb.vacodemaker;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

public class BinWriter 
{
	public static void writeImg(BufferedImage img, FileOutputStream fos) throws Exception
	{
		int index = 0;
		int width = img.getWidth();
		int height = img.getHeight();
        int tripleWidth = width * 3;
        int[] px = new int[width * height];
        byte[] rgbs = new byte[tripleWidth * height];
        
        px = img.getRGB(0, 0, width, height, px, 0, width);
        
        for (int i = height - 1; i >= 0; i--)
        {
            for (int j = 0; j < width; j++) 
            {
                int pixel = px[i * width + j];
                rgbs[index++] = (byte) pixel;
                rgbs[index++] = (byte) (pixel >>> 8);
                rgbs[index++] = (byte) (pixel >>> 16);
            }
        }
        
        fos.write(rgbs);
	}
	
	public static void writeLable(String lable, FileOutputStream fos) throws Exception
	{
		int len = lable.length();
		fos.write(len);
		
		for(int i = 0; i < len; i++)
		{
			fos.write(lable.charAt(i));
		}
		
	}
}
