package SOAP;

/**
 * SOAP����˳���
 * @author Administrator
 *
 */
public class HelloServer {
	public String sayHello(String name){
		System.out.println("sayHello(" + name +")");
		return "Hello " + name + ", How are you?";
	}
}
