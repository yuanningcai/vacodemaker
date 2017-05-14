package nb.vacodemaker;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.patchca.color.SingleColorFactory;
import org.patchca.font.FontFactory;
import org.patchca.service.ConfigurableCaptchaService;

public class Worker implements Runnable 
{
	public SeqWordFactory swf = null;
	public FontFactory ff = null;
	public String filter_flag = "wrdcbmst";
	public String bin_name = "vacode.bin";
	public boolean use_hollow = false;
	public boolean write_bmp = false;
	public boolean write_png = false;
	public int height = 40;
	public int width = 40;
	public int num = 10;
	
	@Override
	public void run() 
	{
		if(swf == null || !(swf instanceof SeqWordFactory))
		{
			return;
		}
		
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory());
        cs.setFilterFactory(new CombineFilterFactory(filter_flag));
        cs.setWordFactory(swf);
        cs.setHeight(height);
        cs.setWidth(width);
        
        if(ff != null)
        {
        	cs.setFontFactory(ff);
        }
        
        if(use_hollow)
        {
        	cs.setTextRenderer(new HollowTextRenderer());
        }
		
        try
        {
        	FileOutputStream fos = new FileOutputStream(bin_name);
        	
        	for(int i = 0; i < num; i++)
        	{
        		int asc = i;
        		String str = swf.getCurrStr();
        		if(str != null)
        		{
        			char[] ca = str.toCharArray();
            		if(ca != null && ca.length > 0)
            		{
            			asc = Integer.valueOf(ca[0]);
            		}
        		}
        		
        		char[] lable = swf.getCurrLable();
        		BufferedImage img = cs.getCaptcha().getImage();
        		BinWriter.writeLable(lable, fos);
        		BinWriter.writeImg(img, fos);
        		
        		if(write_bmp)
        		{
        			BMPWriter.write(img, new FileOutputStream(String.format("img_%03d.bmp", asc)));
        		}
        		
        		if(write_png)
        		{
        			ImageIO.write(img, "png", new FileOutputStream(String.format("img_%03d.png", asc)));
        		}
        		
        		if(i % 20 == 0)
        		{
        			Thread.sleep(1000);
        		}
        	}
        	
        	fos.close();
        }
        catch(Exception e)
        {
        	
        }
	}

}
