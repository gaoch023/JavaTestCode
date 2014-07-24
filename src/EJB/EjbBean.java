package EJB;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 接下来就是客户端调用了，开发EjbBean.java
 * @author Administrator
 *
 */
public class EjbBean {
	/**
	 * 通过调用ejb服务计算结果
	 * @param x
	 * @param y
	 * @param operator
	 * @param type 1-local，2-remote
	 * @return
	 */
	public double Calc(double x,double y,String operator,int type)
	{
		Properties props = new Properties();        
        props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.provider.url", "localhost:1099");
        InitialContext ctx =null;
		try {
			ctx = new InitialContext(props);
			if(type==1)
	        {
				ILocalCalculator calculator=(ILocalCalculator)ctx.lookup("CalculatorBean/local");
				if(operator.equals("+"))
				{
					return calculator.add(x, y);
				}
				else if(operator.equals("-"))
				{
					return calculator.minus(x, y);
				}
				else if(operator.equals("*"))
				{
					return calculator.mutilply(x, y);
				}
				else if(operator.equals("/"))
				{
					return calculator.devide(x, y);
				}
	        }
	        else if(type==2)
	        {
	        	IRemoteCalculator calculator=(IRemoteCalculator)ctx.lookup("CalculatorBean/remote");
	        	
	        	if(operator.equals("+"))
				{
					return calculator.add(x, y);
				}
				else if(operator.equals("-"))
				{
					return calculator.minus(x, y);
				}
				else if(operator.equals("*"))
				{
					return calculator.mutilply(x, y);
				}
				else if(operator.equals("/"))
				{
					return calculator.devide(x, y);
				}
				else if(operator.equals("power"))
				{
					return calculator.power(x, (int)y);
				}
	        }
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return 0.0001;
	}
}
