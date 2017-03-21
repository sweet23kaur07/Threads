package com.threads.producer.consumer;

public class ThreadClass implements Runnable{

	private ProducerConsumer pc;
	private String thredType;

	public ThreadClass(ProducerConsumer pc, String threadType) {
		this.pc = pc;
		this.thredType = threadType;
	}

	@Override
	public void run() {
		int i= 0;
		System.out.println("Inside run");
		while (i<10){
			if(thredType.equalsIgnoreCase("producer")){
				pc.producer();
			} else if(thredType.equalsIgnoreCase("consumer")){
				pc.consumer();
			}
			i++;
		}
	}
}
