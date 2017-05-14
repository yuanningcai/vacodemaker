package nb.vacodemaker;

public class BootStrap 
{
	public static void main(String[] args)
	{
		String[] filter_flags = {"", "", "t"};
		boolean[] use_hollow_flags = {false, true, true};
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
			
	    	SeqCharFactory scf = new SeqCharFactory(sb.toString());
			
			for(int i = 1; i <= max_workers; i++)
			{
				Worker w = new Worker();
				w.swf = scf;
				w.bin_name = String.format("vacode_%02d.bin", i);
				w.filter_flag = filter_flags[(i - 1) % filter_flags.length];
				w.use_hollow = use_hollow_flags[(i - 1) % use_hollow_flags.length];
				w.write_bmp = false;
				w.write_png = false;
				w.height = 40;
				w.width = 40;
				w.num = 300 * 52;

				new Thread(w).start();
			}
		}
		catch(Exception e)
		{
			
		}
		
		
	}
	

}
