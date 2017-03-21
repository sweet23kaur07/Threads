package com.threads.threadpool.executor;

public class TestCustomThreadPool {

	public static void main(String[] args) {

		int noOfThreads = 5;
		BlockingQueueCustom<Task> queue = new BlockingQueueCustom<Task>(10);
		ThreadPool<Task> threadPool = new ThreadPool<Task>(queue, noOfThreads);
		for(int i = 0 ; i < noOfThreads; i++){
			threadPool.execute(new Task());
		}
		threadPool.shutDown();
	}
}

class Task implements Runnable{
	
	@Override
	public void run() {
		System.out.println("Task class task");
	}
}
