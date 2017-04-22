package nb.vacodemaker;

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
        
        try
        {
        	FileOutputStream fos = new FileOutputStream(bin_name);
        	
        	for(int i = 0; i < 10; i++)
        	{
        		BinWriter.writeLable(snf.getCurrStr(), fos);
        		BinWriter.writeImg(cs.getCaptcha().getImage(), fos);
        	}
        	
        	fos.close();
        }
        catch(Exception e)
        {
        	
        }
		
	}

}
