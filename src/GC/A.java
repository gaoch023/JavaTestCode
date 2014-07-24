package GC;

/**
 * gc≤‚ ‘
 * @author Administrator
 *
 */
public class A {
	private static int count = 0;
	
	public static void main(String[] agrs){
		int num = 100000;
		A a;
		
		for(int i=0; i<num; i++){
			 a = new A();
			 a = null;
		}
		
	//	System.gc();
		System.out.println("this is a gc test");
		
		try{
			//’ª≤‚ ‘
			Add(count);
		}catch(StackOverflowError e){
			System.out.println("count£∫" + count + "’ª“Á≥ˆ");
		}
		
		
		
	}
	
	/**
	 * µ›πÈ‘ˆº”
	 * @param count
	 */
	public static void Add(int num){
		num++;
		System.out.println("count£∫" + num);
		Add(num);
		
	}

}
