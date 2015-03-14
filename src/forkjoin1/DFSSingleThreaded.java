package forkjoin1;

import java.io.File;

public class DFSSingleThreaded {
	public static int counter;
	public static int counter2;
String a="apple";
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		File root = new File("J:/");
		listFile(root);
		System.out.println(counter);
		System.out.println(counter2);
		System.out.println("Time taken:"
				+ (System.currentTimeMillis() - startTime));
	}

	public static void listFile(File parentFile) {
		File[] listFiles = parentFile.listFiles();
		for (File file : listFiles) {
			if (file.isDirectory()) {
				counter2++;
				listFile(file);
			} else {
				counter++;
				System.out.println(file.getAbsolutePath());
			}
		}
	}
}
