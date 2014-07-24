package EJB;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * 实现一个无状态的Session Bean,CalculatorBean.java
 * @author Administrator
 *
 */
@Stateless
@Remote({IRemoteCalculator.class})
@Local({ILocalCalculator.class})
public class CalculatorBean implements IRemoteCalculator {

	@Override
	public double add(double x, double y) {
		return x+y;
	}

	@Override
	public double devide(double x, double y) {
		
		if(y==0)
		{
			return 0;
		}
		return x/y;
	}

	@Override
	public double minus(double x, double y) {
		return x-y;
	}

	@Override
	public double mutilply(double x, double y) {
		return x*y;
	}

	@Override
	public double power(double x,int n) {
		double result=1;
		
		for(int i=1;i<=n;i++)
		{
			result*=x;
		}
		
		return result;
	}

}
