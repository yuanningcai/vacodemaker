package nb.vacodemaker;

import java.awt.Font;

import org.patchca.font.FontFactory;

public class SimpleFontFactory implements FontFactory
{
	public String family;
	public boolean bold;
	public int size;
	
	public SimpleFontFactory()
	{
		family = "Tahoma";
		bold = false;
		size = 45;
	}
	
	@Override
	public Font getFont(int index) 
	{
		return new Font(family, bold ? Font.BOLD : Font.PLAIN, size);
	}

}
