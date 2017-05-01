package nb.vacodemaker;

import org.patchca.word.WordFactory;

public class SeqCharFactory implements WordFactory
{
	private String char_seq;
	private String curr_char;
	private int idx;
	
	public SeqCharFactory(String seq) throws Exception
	{
		if(seq == null || seq == "")
		{
			throw new Exception("empty string");
		}
		
		char_seq = seq;
		idx = 0;
		curr_char = seq.substring(0, 1);
	}
	
	public String getCurrStr()
	{
		return char_seq.substring(idx, idx + 1);
	}

	public String getNextWord()
	{
		curr_char = char_seq.substring(idx, idx + 1);
		
		idx++;
		if(idx >= char_seq.length() || idx < 0)
		{
			idx = 0;
		}
		
		return curr_char;
	}
}
