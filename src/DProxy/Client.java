package DProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理场景
 * @author Administrator
 *
 */
public class Client {
	
	public static void main(String[] args){
		Subject subject = new RealSubject();
		
		InvocationHandler handler = new SubjectHandler(subject);
		
		ClassLoader cl = subject.getClass().getClassLoader();
		
		
		Subject proxy = (Subject)Proxy.newProxyInstance(cl, subject.getClass().getInterfaces(), handler);
		
		//执行具体主题角色方法
		proxy.request();
	}
}
