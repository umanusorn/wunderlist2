package com.vi8e.um.wunderlist.utils.dropbox;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by um.anusorn on 10/10/2015.
 */
public class UploadExecutor {

public static void main(String[] args) {

	ExecutorService executor = Executors.newFixedThreadPool ( 5 );

	String[] fileLocations = new String[10];

	for (int i = 0; i < 10; i++) {

		Runnable worker = new UploadThread(fileLocations[i]);

		executor.execute(worker);
	}
	executor.shutdown();

	while (!executor.isTerminated()) { }

	System.out.println("Finished uploading");
}
}