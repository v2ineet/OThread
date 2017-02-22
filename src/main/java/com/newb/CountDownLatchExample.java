package com.newb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


class Processor implements Runnable {
	CountDownLatch latch = null;
	private int count;
	ReentrantLock lock = new ReentrantLock();
	
	public Processor(CountDownLatch latch){
		this.latch = latch;
	}
	
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		lock.lock();
		count++;
		System.out.println("Task Executed");
		lock.unlock();
		latch.countDown();

	
		
	}
}



public class CountDownLatchExample {


	
	
	public static void main(String[] args) {
		
		ExecutorService eservice = Executors.newFixedThreadPool(100);
		
		CountDownLatch latch = new CountDownLatch(10);
		
		for(int i = 0; i < 100; i++){
			eservice.submit(new Processor(latch));
		}
		
		try {
			latch.await(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("10 Threads executed");
			
		
		try {
			eservice.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
