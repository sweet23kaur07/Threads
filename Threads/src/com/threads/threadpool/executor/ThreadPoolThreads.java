package com.threads.threadpool.executor;

public class ThreadPoolThreads<T extends Runnable> extends Thread{
	BlockingQueueCustom<T> tasks;
	ThreadPool<T> pool;

	public ThreadPoolThreads(BlockingQueueCustom<T> tasks, ThreadPool<T> pool) {
		this.tasks = tasks;
		this.pool = pool;
	}
	
	@Override
	public void run() {
		try{
			while(true){
				if(pool.isShutdownInitiated() && tasks.size() ==0){
					this.interrupt();
					Thread.sleep(1000);
				}
				
				T task = tasks.get();
				System.out.println("Thread "  + Thread.currentThread().getName() + " has taken task.");
				task.run();
				System.out.println("Thread "  + Thread.currentThread().getName() + " has completed task.");
			}
		}catch(Exception e){
			System.out.println("Thread " + Thread.currentThread().getName() + " has been stopped");
		}
		
	}
}
