package nb.vacodemaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
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
		TextString ts = convertToSimpleCharacters(text, g, fontFactory, colorFactory);
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
			SimpleTextCharacter stc = (SimpleTextCharacter)tc;
			g.drawString(stc.iterator(), x, y);
		}
	}
	
	protected TextString convertToSimpleCharacters(String text, Graphics2D g, FontFactory fontFactory, ColorFactory colorFactory) {
		TextString characters = new TextString();
		FontRenderContext frc = g.getFontRenderContext();
		double lastx = 0;
		for (int i = 0; i < text.length(); i++) {
			Font font = fontFactory.getFont(i);
			char c = text.charAt(i);
			FontMetrics fm = g.getFontMetrics(font);
			Rectangle2D bounds = font.getStringBounds(String.valueOf(c), frc);
			SimpleTextCharacter tc = new SimpleTextCharacter();
			tc.setCharacter(c);
			tc.setFont(font);
			tc.setWidth(fm.charWidth(c));
			tc.setHeight(fm.getAscent() + fm.getDescent());
			tc.setAscent(fm.getAscent());
			tc.setDescent(fm.getDescent());
			tc.setX(lastx);
			tc.setY(0);
			tc.setFont(font);
			tc.setColor(colorFactory.getColor(i));
			lastx += bounds.getWidth();
			characters.addCharacter(tc);
		}
		return characters;
	}
}
