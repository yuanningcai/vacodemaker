package nb.vacodemaker;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.*;
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
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

        try
        {
        	FileOutputStream fos = new FileOutputStream("vacodemaker_demo.png");
        	EncoderHelper.getChallangeAndWriteImage(cs, "png", fos);
        	fos.close();
        }
        catch(Exception e)
        {
        	
        }
	}
}
