package ReflectDProxy;


/**
 * ������ö�̬����
 * @author Administrator
 *
 */
public class Client {
	public static void main(String[] args){
		Animal jerry = new Rat();
		
		//jerry ���ӷ�������
		jerry = new DecorateAnimal(jerry, FlyFeature.class);
		jerry.doStuff();
		
		//jerry �����������
		jerry = new DecorateAnimal(jerry, DigFeature.class);
		jerry.doStuff();
	}
}
