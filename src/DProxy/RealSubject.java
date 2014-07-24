package DProxy;

/**
 * 真实角色
 * @author Administrator
 *
 */
public class RealSubject implements Subject{

	@Override
	public void request() {
		// TODO Auto-generated method stub
		//业务逻辑处理
		System.out.print("Request方法调用");
	}

}
