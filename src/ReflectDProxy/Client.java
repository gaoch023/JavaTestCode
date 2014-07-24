package ReflectDProxy;


/**
 * 反射调用动态代理
 * @author Administrator
 *
 */
public class Client {
	public static void main(String[] args){
		Animal jerry = new Rat();
		
		//jerry 增加飞行能力
		jerry = new DecorateAnimal(jerry, FlyFeature.class);
		jerry.doStuff();
		
		//jerry 增加钻地能力
		jerry = new DecorateAnimal(jerry, DigFeature.class);
		jerry.doStuff();
	}
}
