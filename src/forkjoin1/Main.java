package forkjoin1;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool();
		FolderProcessor task = new FolderProcessor("J:/ultimate examples");
		pool.execute(task);

		List<String> results;
		results = task.join();
		for (String string : results) {
			System.out.println(string);
		}
		System.out.println(results.size());
		System.out.println("Time taken:"
				+ (System.currentTimeMillis() - startTime));
		pool.shutdown();
	}
}
