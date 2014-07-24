package ReferenceTest;

import java.util.List;

import ReferenceTest.ReferenceTest.Bean;

/**
 * ²Ù×÷Àà
 * @author Administrator
 *
 */
public class Utils {
	public void add(List<String> strList, List<Bean> beanList){
		strList.add("1");
		beanList.add(new Bean());
		
		System.out.println(strList.size() + ":" + beanList.size());
	}
	
	public boolean add2(List<String> strList, List<Bean> beanList){
		strList.add("1");
		beanList.add(new Bean());
		
		System.out.println(strList.size() + ":" + beanList.size());
		
		return false;
	}
	
	public void setValue(String str){
		str = "lisi";
	}
	
	public boolean setValue2(String str){
		str = "wangwu";
		return false;
	}

}
