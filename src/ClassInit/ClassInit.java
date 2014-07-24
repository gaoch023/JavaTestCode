package ClassInit;

/**
 * 类初始化顺序
 * @author Administrator
 *
 */
public class ClassInit {
	{
		System.out.println("初始化代码块");
	}
	
	static {
		System.out.println("static 初始化代码块");
	}
	
	public ClassInit(){
		System.out.println("ClassInitg构造函数执行");
	}
	
	public static void main(String[] args){
		new Sub();
	}
}
