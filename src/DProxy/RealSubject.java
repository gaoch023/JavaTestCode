package DProxy;

/**
 * ��ʵ��ɫ
 * @author Administrator
 *
 */
public class RealSubject implements Subject{

	@Override
	public void request() {
		// TODO Auto-generated method stub
		//ҵ���߼�����
		System.out.print("Request��������");
	}

}
