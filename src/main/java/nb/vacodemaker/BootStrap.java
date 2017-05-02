package nb.vacodemaker;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BootStrap 
{
	public static void main(String[] args)
	{
		String[] filter_flags = {"rdcb", "cbmsw", "dcbms", "bcwsr"};
		int max_workers = 1;
		
		for(int i = 1; i <= max_workers; i++)
		{
			String filter_flag = filter_flags[i % filter_flags.length];
			String bin_name = String.format("nbmnist_%02d.bin", i);
			new Thread(new Worker("t", bin_name)).start();
		}
	}
	

}
