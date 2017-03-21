package com.threads.threadpool.executor;

public class ThreadPool<T extends Runnable> {
	private static boolean isShutdownInitiated = false;
	BlockingQueueCustom<T> tasks;
	
	public ThreadPool(BlockingQueueCustom<T> tasks, int noOfThreads) {
		this.tasks = tasks;
		for(int i = 0; i < noOfThreads; i++){
			ThreadPoolThreads<T> thread = new ThreadPoolThreads<T>(tasks,this);
			thread.setName("Thread-" + i);
			thread.start();
		}
	}
	
	public void execute(T task){
		if(isShutdownInitiated()){
			System.out.println("Shutdown has already been called");
			throw new RuntimeException("Cannot add more task to pool, shutdown has already been called");
		}
		System.out.println("Adding new task in queue");
		tasks.put(task);
	}

	public void shutDown(){
		setShutdownInitiated(true);
	}

	public static boolean isShutdownInitiated() {
		return isShutdownInitiated;
	}

	public static void setShutdownInitiated(boolean isShutdownInitiated) {
		ThreadPool.isShutdownInitiated = isShutdownInitiated;
	}
	
}
