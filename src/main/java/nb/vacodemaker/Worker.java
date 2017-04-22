package nb.vacodemaker;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.patchca.color.SingleColorFactory;
import org.patchca.service.ConfigurableCaptchaService;

public class Worker implements Runnable 
{
	String filter_flag = "rdcbmsw";
	String bin_name = "vacode.bin";
	
	public Worker(String filter_flag, String bin_name)
	{
		this.filter_flag = filter_flag;
		this.bin_name = bin_name;
	}
	
	@Override
	public void run() 
	{
		SeqNumFactory snf = new SeqNumFactory();
		snf.setMin(0);
		snf.setMax(9);
		snf.setFormat("%d");
		
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory());
        cs.setFilterFactory(new CombineFilterFactory(filter_flag));
        cs.setWordFactory(snf);
        cs.setHeight(60);
        cs.setWidth(60);
        
        try
        {
        	FileOutputStream fos = new FileOutputStream(bin_name);
        	
        	for(int i = 0; i < 6000; i++)
        	{
        		String lable = snf.getCurrStr();
        		BufferedImage img = cs.getCaptcha().getImage();
        		BinWriter.writeLable(lable, fos);
        		BinWriter.writeImg(img, fos);
        		Thread.sleep(200);
        		//BMPWriter.write(img, new FileOutputStream(String.format("img_%s.bmp", lable)));
        	}
        	
        	fos.close();
        }
        catch(Exception e)
        {
        	
        }
		
	}

}
