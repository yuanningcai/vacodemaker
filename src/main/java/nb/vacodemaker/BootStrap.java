package nb.vacodemaker;

import java.util.Arrays;

import org.patchca.font.RandomFontFactory;

public class BootStrap 
{
	public static void main(String[] args)
	{
		String[] fonts = {"Alfredo Heavy Hollow", "MY TURTLE", "FZ ROMAN 28 HOLLOW EX", "王漢宗新粗標魏碑空心"};
		String[] filter_flags = {"", "", "", ""};
		boolean[] use_hollow_flags = {false, false, false, false};
		int max_workers = 30;
		
		try
		{
			//0000~9999
	    	SeqNumFactory snf = new SeqNumFactory();
			snf.setMin(0);
			snf.setMax(9999);
			snf.setFormat("%04d");
			
			//ascii
			StringBuilder sb = new StringBuilder();
			for(int i = 65; i <= 90; i++)
			{
				sb.append((char)i);
			}
			
			for(int i = 97; i <= 122; i++)
			{
				sb.append((char)i);
			}
			
			for(int i = 1; i <= max_workers; i++)
			{
				Worker w = new Worker();
				w.swf = new SeqCharFactory(sb.toString());
				w.bin_name = String.format("vacode_%02d.bin", i);
				w.write_bmp = false;
				w.write_png = false;
				w.height = 40;
				w.width = 40;
				w.num = 200 * 52;
				
				RandomFontFactory rff = new RandomFontFactory();
				rff.setRandomStyle(true);
				rff.setFamilies(Arrays.asList(fonts));
				w.ff = rff;
				
				if(filter_flags.length > 0)
				{
					w.filter_flag = filter_flags[(i - 1) % filter_flags.length];
				}
				
				if(use_hollow_flags.length > 0)
				{
					w.use_hollow = use_hollow_flags[(i - 1) % use_hollow_flags.length];
				}
				
				new Thread(w).start();
			}
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
	}
	

}
