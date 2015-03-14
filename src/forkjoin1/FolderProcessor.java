package forkjoin1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {
	// generic type of RecursiveTask is the output of compute()

	String path;

	public FolderProcessor(String path) {
		super();
		this.path = path;
	}

	@Override
	protected List<String> compute() {
		List<String> filelist = new ArrayList<String>();
		List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();

		File file = new File(path);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				// create and start sub task
				FolderProcessor task = new FolderProcessor(
						childFile.getAbsolutePath());
				task.fork();
				tasks.add(task);
			} else {
				filelist.add(childFile.getAbsolutePath());
			}

		}

		// collect output from sub tasks
		for (FolderProcessor task : tasks) {
			filelist.addAll(task.join());
		}

		return filelist;
	}

}
