package ReferenceTest;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationEvent;


/**
 * “˝”√≤‚ ‘
 * @author Administrator
 *
 */
public class ReferenceTest {
	static String str= "hello world";
	static int index = 0;
	
	public static void main(String[] args){
		List<String> strList = new ArrayList<String>();
		List<Bean> beanList = new ArrayList<Bean>();
		
		Utils utils = new Utils();
		
		utils.add(strList, beanList);
		
		strList.clear();
		beanList.clear();
		
		utils.add2(strList, beanList);
		
		utils.setValue(str);
		System.out.println(str);
		
		utils.setValue2(str);
		System.out.println(str);
		
		setValue3(str);
		System.out.println(str);
		
		setValue4(str);
		System.out.println(str);
		
		setInt(index);
		System.out.println(index + "");
		
		setInt2(index);
		System.out.println(index + "");
	}
	
	public static class Bean{
		String name;
	}
	
	public static boolean setValue3(String str){
		str = "zhangsan";
		return false;
	}
	
	public static void setValue4(String str){
		str = "zhangsan2";
	}
	
	public static boolean setInt(int  vaule){
		vaule = -1;
		return false;
	}
	
	public static void setInt2(int  vaule){
		vaule = -2;
	}
}
