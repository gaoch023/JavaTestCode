package RuntimeConstantPoolOOM;

import java.util.ArrayList;
import java.util.List;

/**
 * �������������
 * @author Administrator
 *
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) throws InterruptedException{
		//ʹ��List�����ų����ص�����������full gc����
		List<String> list = new ArrayList<String>();
		
		int i=0;
		
		while(true){
			list.add(String.valueOf(i++).intern());
			System.out.println(i + "");
		}
		
	}
}
