package com.newb;

public class ThreeThread1 {

	Object lock1 = new Object();  //all thread to share the same lock
	Object lock2 = new Object();  //all thread to share the same lock
	Object lock3 = new Object();  //all thread to share the same lock

	Thread t1 = new Thread(new Runnable() {

		@Override
		public void run() {
			for (int i = 1; i < 13; i = i + 3) {

				synchronized (lock1) {
					

					System.out.println(i);
					
					try {
						lock1.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					

				}
				
				
				synchronized (lock1) {
					
				}
				lock2.notify();
				lock3.notify();

				
				
			}
		}

	});

	Thread t2 = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			for (int i = 2; i < 13; i = i + 3) {

				synchronized (lock2) {
					
					System.out.println(i);

					
					try {
						lock2.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lock3.notify();
					lock1.notify();
				}

			}
		}

	});

	Thread t3 = new Thread(new Runnable() {

		@Override
		public void run() {
			
			for (int i = 3; i < 13; i = i + 3) {
				synchronized (lock3) {
					System.out.println(i);

					try {
						lock3.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					lock1.notify();
					lock2.notify();
				}
			}

		}

	});

	public ThreeThread1() {
		t1.start();
		t2.start();
		t3.start();

		waitForAll();
	}

	private void waitForAll() {
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new ThreeThread1();

	}

}
