package ClassInit;

/**
 * 子类
 * @author Administrator
 *
 */
public class Sub extends ClassInit{
	{
		System.out.println("sub 初始化代码块");
	}
	
	static{
		System.out.println("sub static 初始化代码块");
	}
	
	public Sub(){
		System.out.println("sub 构造函数执行");
	}
	
	
}
