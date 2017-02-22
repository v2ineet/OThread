package com.newb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadExample {

	List<Integer> list = new ArrayList<Integer>();
	ReentrantLock rlock = new ReentrantLock();
	
	public ThreadExample(){
	}
	
	
	private void addToList(int i){
		rlock.lock();
		list.add(i);
		rlock.unlock();
	}

	private void threadTest(){
		
		ExecutorService ex = Executors.newFixedThreadPool(10);
		
		
		
		Future fut1 = ex.submit(new Runnable() {
			
			@Override
			public void run() {
				for(int i =0; i < 1000; i++){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					addToList(i);
				}
				
			}
		});
		
		Future fut2 = ex.submit(new Runnable() {
			
			@Override
			public void run() {
				for(int i =0; i < 1000; i++){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					addToList(i);
				}
				
			}
		});
		
		ex.shutdown();
		try {
			ex.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Out Here list" + list.size());
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ThreadExample te = new ThreadExample();
		te.threadTest();
		
	}

}
