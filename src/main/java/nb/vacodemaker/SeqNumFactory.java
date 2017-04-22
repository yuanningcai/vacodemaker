package nb.vacodemaker;

import org.patchca.word.WordFactory;

public class SeqNumFactory implements WordFactory
{
	private int curr = 0;
	private int min = 0;
	private int max = 9999;
	private int step = 1;
	private String format = "%04d";
	
	public int getCurr() 
	{
		return curr;
	}

	public int getMin() 
	{
		return min;
	}

	public void setMin(int min) 
	{
		this.min = min;
	}

	public int getMax() 
	{
		return max;
	}

	public void setMax(int max) 
	{
		this.max = max;
	}

	public int getStep() 
	{
		return step;
	}

	public void setStep(int step) 
	{
		this.step = step;
	}

	public String getFormat() 
	{
		return format;
	}

	public void setFormat(String format) 
	{
		this.format = format;
	}

	public String getNextWord()
	{
		if(curr < min)
		{
			curr = min;
		}
		
		String nw = String.format(format, curr);
		
		curr += step;
		if(curr > max)
		{
			curr = min;
		}
		
		return nw;
	}
}
