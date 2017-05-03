package nb.vacodemaker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.patchca.filter.library.AbstractImageOp;

public class SimpleTransformImageOp extends AbstractImageOp
{
	private int max_rotate_degree = 45;
	private double min_scale = 0.5;
	
	public int getMax_rotate_degree() 
	{
		return max_rotate_degree;
	}

	public void setMax_rotate_degree(int max_rotate_degree) 
	{
		this.max_rotate_degree = max_rotate_degree;
	}

	public double getMin_scale() 
	{
		return min_scale;
	}

	public void setMin_scale(double min_scale) 
	{
		this.min_scale = min_scale;
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) 
	{
		if (dest == null) 
		{
			dest = createCompatibleDestImage(src, null);
		}
		
		double width = dest.getWidth();
		double height = dest.getHeight();
		Graphics2D g2 = (Graphics2D) dest.getGraphics();
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		
		Random r = new Random();
		double scale = r.nextDouble() * (1 - min_scale) + min_scale;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, (int)width, (int)height);
		g2.rotate(Math.toRadians(r.nextDouble() * max_rotate_degree * 2 - max_rotate_degree), width / 2, height / 2);
		g2.scale(scale, scale);
		g2.drawImage(src, null, (int)((1.0 - scale) * width), (int)((1.0 - scale) * height));
		g2.dispose();
		
		return dest;
	}
}
