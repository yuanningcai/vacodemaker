package nb.vacodemaker;

import org.patchca.color.GradientColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.*;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

public class bootstrap 
{
	public static void main(String[] args)
	{
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new GradientColorFactory());
        cs.setFilterFactory(new DiffuseRippleFilterFactory());
        SeqNumFactory snf = new SeqNumFactory();
        cs.setWordFactory(snf);

        try
        {
        	for(int i = 0; i < 10; i++)
        	{
        		FileOutputStream fos = new FileOutputStream(String.format("vacode_%d.bmp", i));
        		System.out.println(snf.getCurr());
        		//EncoderHelper.getChallangeAndWriteImage(cs, "jpg", fos);
        		Captcha captcha = cs.getCaptcha();
        		BMPWriter.write(captcha.getImage(), fos);
        		fos.close();
        	}
        }
        catch(Exception e)
        {
        	
        }
	}
}
