package nb.vacodemaker;

import java.awt.Color;
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
        try
        {
        	//SeqNumFactory snf = new SeqNumFactory();
    		//snf.setMin(0);
    		//snf.setMax(9999);
    		//snf.setFormat("%04d");
    		
    		StringBuilder sb = new StringBuilder();
    		for(int i = 65; i < 75; i++)
    		{
    			sb.append((char)i);
    		}
    		
        	SeqCharFactory scf = new SeqCharFactory(sb.toString());
    		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    		cs.setTextRenderer(new HollowTextRenderer());
            cs.setColorFactory(new SingleColorFactory());
            cs.setFilterFactory(new CombineFilterFactory(filter_flag));
            cs.setWordFactory(scf);
            cs.setHeight(40);
            cs.setWidth(40);
            
        	FileOutputStream fos = new FileOutputStream(bin_name);
        	
        	for(int i = 65; i < 75; i++)
        	{
        		String lable = scf.getCurrStr();
        		BufferedImage img = cs.getCaptcha().getImage();
        		BinWriter.writeLable(lable, fos);
        		BinWriter.writeImg(img, fos);
        		BMPWriter.write(img, new FileOutputStream(String.format("img_%s.bmp", lable)));
        		
        		if(i % 100 == 0)
        		{
        			//Thread.sleep(200);
        		}
        	}
        	
        	fos.close();
        }
        catch(Exception e)
        {
        	
        }
	}

}
