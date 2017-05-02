package nb.vacodemaker;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

import org.patchca.filter.AbstractFilterFactory;
import org.patchca.filter.library.BlurImageOp;
import org.patchca.filter.library.CurvesImageOp;
import org.patchca.filter.library.DiffuseImageOp;
import org.patchca.filter.library.MarbleImageOp;
import org.patchca.filter.library.RippleImageOp;
import org.patchca.filter.library.SoftenImageOp;
import org.patchca.filter.library.WobbleImageOp;

public class CombineFilterFactory extends AbstractFilterFactory
{
	private String flags;
	private List<BufferedImageOp> filters;

	public CombineFilterFactory(String flags)
	{
		this.flags = flags;
	}
	
	@Override
	public List<BufferedImageOp> getFilters() 
	{
		if (filters == null) 
		{
			filters = new ArrayList<BufferedImageOp>();
			
			if(flags.indexOf('w') > 0)
			{
				filters.add(new WobbleImageOp());
			}
			
			if(flags.indexOf('r') > 0)
			{
				filters.add(new RippleImageOp());
			}
			
			if(flags.indexOf('d') > 0)
			{
				filters.add(new DiffuseImageOp());
			}
			
			if(flags.indexOf('c') > 0)
			{
				filters.add(new CurvesImageOp());
			}
			
			if(flags.indexOf('b') > 0)
			{
				filters.add(new BlurImageOp());
			}
			
			if(flags.indexOf('m') > 0)
			{
				filters.add(new MarbleImageOp());
			}
			
			if(flags.indexOf('s') > 0)
			{
				filters.add(new SoftenImageOp());
			}
			
			if(flags.indexOf('t') > 0)
			{
				filters.add(new SimpleTransformImageOp());
			}
		}
		
		return filters;
	}
}
