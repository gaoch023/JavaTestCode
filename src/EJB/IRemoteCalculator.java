package EJB;

/**
 * 远程访问接口，IRemoteCalculator.java,继承自本地接口
 * 
 * @author Administrator
 * 
 */
public interface IRemoteCalculator extends ILocalCalculator {
	double power(double x, int n);
}
