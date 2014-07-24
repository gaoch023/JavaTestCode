package RuntimeConstantPoolOOM;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法区溢出测试
 * @author Administrator
 *
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) throws InterruptedException{
		//使用List保存着常量池的索引，避免full gc回收
		List<String> list = new ArrayList<String>();
		
		int i=0;
		
		while(true){
			list.add(String.valueOf(i++).intern());
			System.out.println(i + "");
		}
		
	}
}
