package ClassInit;

/**
 * ���ʼ��˳��
 * @author Administrator
 *
 */
public class ClassInit {
	{
		System.out.println("��ʼ�������");
	}
	
	static {
		System.out.println("static ��ʼ�������");
	}
	
	public ClassInit(){
		System.out.println("ClassInitg���캯��ִ��");
	}
	
	public static void main(String[] args){
		new Sub();
	}
}
