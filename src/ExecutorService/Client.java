package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试, 可以发现线程被循环创建，但是启动线程却不是连续的，而是由ExecutorService决定的。
 * @author Administrator
 *
 */
public class Client {
	public static void main(String[] args) throws InterruptedException{
		TestRunnable runnable;
		// 创建一个固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            System.out.println("创建线程" + i);
            
            runnable = new TestRunnable("" + i);
            // 在未来某个时间执行给定的命令
            service.execute(runnable);
        }
        // 关闭启动线程
        service.shutdown();
        
        // 等待子线程结束，再继续执行下面的代码
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("all thread complete");

	}
	
	static class TestRunnable implements Runnable{
		private String name;
		
		public TestRunnable(String name){
			this.name = name;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("线程" + name  + "开始运行");
		}
		
	}
}
