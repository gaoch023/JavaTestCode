package StringIntern;

/**
 * �ַ���פ��
 * @author Administrator
 *
 */
public class StringIntern {
	
	public static void main(String[] args){
		String name = new String("����");
		String name2 = new String("����");
		
		if(name == name2){
			System.out.println("name �� name2 ��ͬ");
		}else{
			System.out.println("name �� name2 ����ͬ");
		}
		
		if(name.equals(name2)){
			System.out.println("name equals name2 ��ͬ");
		}else{
			System.out.println("name equals  name2 ����ͬ");
		}
		
		
		if(name == "����"){
			System.out.println("name  name2 ��ͬ");
		}else{
			System.out.println("name name2 ����ͬ");
		}
		
		name = name.intern();
		
		if(name == "����"){
			System.out.println("name  name2 ��ͬ");
		}else{
			System.out.println("name name2 ����ͬ");
		}
		
		
		if(name2 == name){
			System.out.println("name  name2 ��ͬ");
		}else{
			System.out.println("name name2 ����ͬ");
		}
	}

}
