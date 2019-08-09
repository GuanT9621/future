package concurrence.teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class teache {

	static BlockingQueue<String> list = new LinkedBlockingQueue<String>();
	
	public static void main(String[] args) {
		Runnable stud1 = new Runnable() {
			@Override
			public void run() {
				// synchronized (list) {
					for(int i = 0; i < 100; i++) {
						list.add(" A"  + i);
					}
				// }
			}
		};
		Runnable stud2 = new Runnable() {
			@Override
			public void run() {
				// synchronized (list) {
					for(int i = 0; i < 100; i++) {
						list.add(" B" + i);
					}
				// }
			}
		};
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.execute(stud1);
		executorService.execute(stud2);
		
		executorService.shutdown();
		
		if (executorService.isShutdown()) {
			System.out.println(list);
			System.out.println(list.size());
		}
		
	}
}
