package DProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectHandler implements InvocationHandler{
	//������Ķ���
	private  Subject subject;
	
	public SubjectHandler(Subject subject){
		this.subject = subject;
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//Ԥ����
		System.out.print("Ԥ����");
		//ֱ�ӵ��ñ�������ķ���
		Object obj = method.invoke(subject, args);
		//����
		System.out.print("����");
		return obj;
	}

}
