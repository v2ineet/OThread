package com.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	
	class Process1 implements Runnable{
		CyclicBarrier barrier;
		
		public Process1(CyclicBarrier barrier){
			this.barrier = barrier;
		}

		@Override
		public void run() {
			p("barrier.getParties() "+ barrier.getParties());
			try {
				p("barrier.getNumberWaiting(): " + barrier.getNumberWaiting());
				barrier.await();
				p("barrier broken");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Process2 implements Runnable{
		CyclicBarrier barrier;
		
		public Process2(CyclicBarrier barrier){
			this.barrier = barrier;
		}

		@Override
		public void run() {
			p("barrier.getParties() "+ barrier.getParties());
			try {
				Thread.sleep(1000);
				p("barrier.getNumberWaiting(): " + barrier.getNumberWaiting());
				barrier.await();
				p("barrier broken");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	class Process3 implements Runnable{
		CyclicBarrier barrier;
		
		public Process3(CyclicBarrier barrier){
			this.barrier = barrier;
		}

		@Override
		public void run() {
			p("barrier.getParties() "+ barrier.getParties());
			try {
				p("sleep....");
				Thread.sleep(5000);
				p("awoke la la la");
				
				p("barrier.getNumberWaiting(): " + barrier.getNumberWaiting());
				barrier.await();
				p("barrier broken");
				p("barrier.getNumberWaiting(): " + barrier.getNumberWaiting());
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public CyclicBarrierDemo(){
		
		CyclicBarrier cb = new CyclicBarrier(3);
		
		Process1 p1 = new Process1(cb);
		Process2 p2 = new Process2(cb);
		Process3 p3 = new Process3(cb);

		Thread t1 = new Thread(null, p1, "T1");
		Thread t2 = new Thread(null, p2, "T2");
		Thread t3 = new Thread(null, p3, "T3");

		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		p("Done...");
	}
	
	
	public static void p(String msg){
		System.out.println(Thread.currentThread().getName()+ ": " + msg);
	}
	
	
	public static void main(String[] args){
		new CyclicBarrierDemo();
	}

}
