package ReflectDProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class DecorateAnimal implements Animal{
	//����װ�Ķ���
	private Animal animal;
	
	//ʹ����һ�ְ�װ��
	private Class<? extends Feature> clz;
	
	public DecorateAnimal(Animal animal, Class<? extends Feature> clz) {
		super();
		this.animal = animal;
		this.clz = clz;
	}

	@Override
	public void doStuff() {
		// TODO Auto-generated method stub
		InvocationHandler handler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// TODO Auto-generated method stub
				Object obj = null;
				
				//���ð�װ����
				if(Modifier.isPublic(method.getModifiers())){
					obj = method.invoke(clz.newInstance(), args);
				}
				animal.doStuff();
				
				return obj;
			}
		};
		
		//��ǰ�������
		ClassLoader cl = getClass().getClassLoader();
		
		Feature proxy = (Feature)Proxy.newProxyInstance(cl, clz.getInterfaces(), handler);
		proxy.load();
	}

}
