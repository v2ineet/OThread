package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeThreadLock {

	Lock lock = new ReentrantLock();

	Condition cond1 = lock.newCondition();
	Condition cond2 = lock.newCondition();
	Condition cond3 = lock.newCondition();

	private volatile int nextThread = 1;

	class ThreadA implements Runnable {

		@Override
		public void run() {
			for (int i = 1; i < 100; i += 3) {

				lock.lock();

				while (nextThread != 1) {
					try {
						cond1.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				System.out.println("i : " + i);

				nextThread = 2;
				cond2.signalAll();
				lock.unlock();

			}
		}
	};

	class ThreadB implements Runnable {

		@Override
		public void run() {

			for (int i = 2; i < 100; i += 3) {

				lock.lock();

				while (nextThread != 2) {
					try {
						cond2.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				System.out.println("i : " + i);

				nextThread = 3;
				cond3.signalAll();
				lock.unlock();
			}

		}
	};

	class ThreadC implements Runnable {

		@Override
		public void run() {
			for (int i = 3; i < 100; i += 3) {

				lock.lock();

				while (nextThread != 3) {
					
					try {
						cond3.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				System.out.println("i : " + i);
				
				nextThread = 1;
				cond1.signalAll();
				lock.unlock();
			}

		}

	};

	public void startThreads() {

		ExecutorService service = Executors.newFixedThreadPool(3);
		service.submit(new ThreadA());
		service.submit(new ThreadB());
		service.submit(new ThreadC());

	}

	public static void main(String... vin) {
		new ThreeThreadLock().startThreads();

	}

}
