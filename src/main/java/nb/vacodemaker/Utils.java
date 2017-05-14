package nb.vacodemaker;

import java.awt.GraphicsEnvironment;

public class Utils 
{
	public static void getAllFonts()
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        String[] allfonts = ge.getAvailableFontFamilyNames();  
        for(String font : allfonts)
        {  
            System.out.println(font);  
        }
	}
}
