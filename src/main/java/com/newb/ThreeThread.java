package com.newb;

public class ThreeThread {

	volatile boolean flag1;
	volatile boolean flag2;
	volatile boolean flag3;
	
	Object lock = new Object();  //all thread to share the same lock

	Thread t1 = new Thread(new Runnable() {

		@Override
		public void run() {
			for (int i = 1; i < 13; i = i + 3) {

				synchronized (lock) {

					while (flag1) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(i);
					flag1 = true;  //i am done printing
					flag2 = false; //go ahead for next thread
					lock.notifyAll();
				}
			}
		}

	});

	Thread t2 = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			for (int i = 2; i < 13; i = i + 3) {

				synchronized (lock) {
					while (flag2 || !flag1) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(i);
					flag2 = true;  //i am done printing
					flag3= false;  //go ahead for next thread
					lock.notifyAll();
				}

			}
		}

	});

	Thread t3 = new Thread(new Runnable() {

		@Override
		public void run() {
			
			for (int i = 3; i < 13; i = i + 3) {
				synchronized (lock) {

					while (flag3 || !flag2 || !flag1) { 
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					System.out.println(i);
					flag3 = true;  //i am done printing
					flag1= false;  //go ahead for next thread
					lock.notifyAll();
				}
			}

		}

	});

	public ThreeThread() {
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

		new ThreeThread();

	}

}
