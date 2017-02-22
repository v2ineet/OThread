package com.base;


public class DeadLockDemo {

	
	Object account1 = new Object();
	Object account2 = new Object();

	class ThreadA implements Runnable{

		@Override
		public void run() {
			p("Try to take lock on account 1");	
			
			
			synchronized(account1){
				p("Success - lock on account 1");	
				p("Now - Try to take lock on account 2");	
				
				synchronized(account2){
					p("Success - lock on account 2");	

				}
				
				p("Mission Success");
			}
		}
	}


	class ThreadB implements Runnable{

		@Override
		public void run() {
			p("Try to take lock on account 2");	
			
			
			synchronized(account2){
				p("Success - lock on account 2");	
				p("Now - Try to take lock on account 1");	
				
				synchronized(account1){
					p("Success - lock on account 1");	

				}
				
				p("Mission Success");

			}
		}
	}

	
	public void deadLock(){
		Thread thread1 = new Thread(new ThreadA());
		thread1.setName("T1");
		
		Thread thread2 = new Thread(new ThreadB());
		thread2.setName("T2");

		
		thread1.start();
		thread2.start();
		
	}
	
	
	public static void main(String args[]){
		
		DeadLockDemo demo = new DeadLockDemo();
		demo.deadLock();

	}
	
	public static void p(String msg){
		System.out.println(Thread.currentThread().getName()+ ": " + msg);
	}

}


