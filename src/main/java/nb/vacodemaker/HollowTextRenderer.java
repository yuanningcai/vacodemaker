package nb.vacodemaker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.patchca.color.ColorFactory;
import org.patchca.font.FontFactory;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.text.renderer.TextCharacter;
import org.patchca.text.renderer.TextString;

public class HollowTextRenderer extends BestFitTextRenderer 
{
	Color bg = Color.WHITE;
	
	public Color getBg() 
	{
		return bg;
	}

	public void setBg(Color bg) 
	{
		this.bg = bg;
	}

	@Override
	public void draw(String text, BufferedImage canvas, FontFactory fontFactory, ColorFactory colorFactory) {
		Graphics2D g = (Graphics2D) canvas.getGraphics();
		TextString ts = convertToCharacters(text, g, fontFactory, colorFactory);
		arrangeCharacters(canvas.getWidth(), canvas.getHeight(), ts);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		for (TextCharacter tc : ts.getCharacters()) {
			float x = (float) tc.getX();
			float y = (float) tc.getY();
			
			g.setColor(tc.getColor());
			g.drawString(tc.iterator(), x - 1, y - 1);
			g.drawString(tc.iterator(), x - 1, y + 1);
			g.drawString(tc.iterator(), x + 1, y - 1);
			g.drawString(tc.iterator(), x + 1, y + 1);
			
			g.setColor(bg);
			g.drawString(tc.iterator(), x, y);
		}
	}
}
