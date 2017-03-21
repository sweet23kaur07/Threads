package com.threads.threadpool.executor;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueCustom<T extends  Runnable> {
	
	private static int limit;
	private Queue<T> queue;
	
	public BlockingQueueCustom(int limit) {
		this.limit = limit;
		queue = new LinkedList<T>();
	}
	
	public synchronized T get(){
		while(queue.size() == 0){
			try {
				System.out.println("Waiting for new task");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		return queue.poll();
	}

	public synchronized void put(T task){
		while(queue.size() == limit){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		queue.add(task);
		notifyAll();
	}
	
	public synchronized int size(){
		return queue.size();
	}
}
