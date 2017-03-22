package com.concurrency;

public class ThreadJoin {

	Thread t = null;
	
	
	public ThreadJoin(){
		t = new Thread(()->{
			
			try {
				System.out.println("Before Join");
				Thread.sleep(1000);
				t.join();
				System.out.println("After Join");

			} catch (InterruptedException e) {
				System.out.println("I have been interrupted");
			}
			
			System.out.println("Here we go ...");

		});
		
		t.start();
		System.out.println("Hello Main Thtread");
		
		try {
			Thread.sleep(5000);
			t.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	
	public static void main(String[] args){
		System.out.println("hello");
		new ThreadJoin();
	}
	
}
