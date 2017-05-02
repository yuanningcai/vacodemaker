package nb.vacodemaker;

import org.patchca.filter.library.AbstractTransformImageOp;

public class SimpleTransformImageOp extends AbstractTransformImageOp
{
	@Override
	protected void transform(int x, int y, double[] t) 
	{
		double Scale = 0.5;
		
		t[0] = x * Scale;
		t[1] = y * Scale;
	}
}
