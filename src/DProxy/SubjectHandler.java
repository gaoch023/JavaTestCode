package DProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectHandler implements InvocationHandler{
	//被代理的对象
	private  Subject subject;
	
	public SubjectHandler(Subject subject){
		this.subject = subject;
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//预处理
		System.out.print("预处理");
		//直接调用被代理类的方法
		Object obj = method.invoke(subject, args);
		//后处理
		System.out.print("后处理");
		return obj;
	}

}
