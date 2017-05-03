package nb.vacodemaker;

import org.patchca.word.WordFactory;

public interface SeqWordFactory extends WordFactory 
{
	public String getCurrStr();
	
	public char[] getCurrLable();
}
