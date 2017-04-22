package nb.vacodemaker;

public class BootStrap 
{
	public static void main(String[] args)
	{
		String[] filter_flags = {"rdcbmsw", "rdcb", "cbmsw", "dcbms", "bcwsr"};
		int max_workers = 1;
		
		for(int i = 0; i < max_workers; i++)
		{
			String filter_flag = filter_flags[i % filter_flags.length];
			String bin_name = String.format("vacode_%02d.bin", i);
			new Thread(new Worker(filter_flag, bin_name)).start();
		}
	}
	

}
