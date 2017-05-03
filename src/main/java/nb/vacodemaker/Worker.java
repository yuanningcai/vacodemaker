package nb.vacodemaker;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.patchca.color.SingleColorFactory;
import org.patchca.service.ConfigurableCaptchaService;

public class Worker implements Runnable 
{
	public SeqWordFactory swf = null;
	public String filter_flag = "wrdcbmst";
	public String bin_name = "vacode.bin";
	public boolean use_hollow = false;
	public boolean write_bmp = false;
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
        
        if(use_hollow)
        {
        	cs.setTextRenderer(new HollowTextRenderer());
        }
		
        try
        {
        	FileOutputStream fos = new FileOutputStream(bin_name);
        	
        	for(int i = 0; i < num; i++)
        	{
        		String str = swf.getCurrStr();
        		char[] lable = swf.getCurrLable();
        		BufferedImage img = cs.getCaptcha().getImage();
        		BinWriter.writeLable(lable, fos);
        		BinWriter.writeImg(img, fos);
        		
        		if(write_bmp)
        		{
        			BMPWriter.write(img, new FileOutputStream(String.format("img_%s.bmp", str)));
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
