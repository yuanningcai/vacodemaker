package nb.vacodemaker;

public class SeqNumFactory implements SeqWordFactory
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
	
	public String getCurrStr()
	{
		return String.format(format, curr);
	}
	
	public char[] getCurrLable()
	{
		char[] ori = getCurrStr().toCharArray();
		char[] ret = new char[ori.length];
		
		for(int i = 0; i < ret.length; i++)
		{
			ret[i] = (char)(ori[i] - '0');
		}
		
		return ret;
	}

	public int getMin() 
	{
		return min;
	}

	public void setMin(int min) 
	{
		this.min = min;
		this.curr = min;
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
