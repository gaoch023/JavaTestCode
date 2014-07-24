package StringIntern;

/**
 * 字符串驻留
 * @author Administrator
 *
 */
public class StringIntern {
	
	public static void main(String[] args){
		String name = new String("张三");
		String name2 = new String("张三");
		
		if(name == name2){
			System.out.println("name 和 name2 相同");
		}else{
			System.out.println("name 和 name2 不相同");
		}
		
		if(name.equals(name2)){
			System.out.println("name equals name2 相同");
		}else{
			System.out.println("name equals  name2 不相同");
		}
		
		
		if(name == "张三"){
			System.out.println("name  name2 相同");
		}else{
			System.out.println("name name2 不相同");
		}
		
		name = name.intern();
		
		if(name == "张三"){
			System.out.println("name  name2 相同");
		}else{
			System.out.println("name name2 不相同");
		}
		
		
		if(name2 == name){
			System.out.println("name  name2 相同");
		}else{
			System.out.println("name name2 不相同");
		}
	}

}
