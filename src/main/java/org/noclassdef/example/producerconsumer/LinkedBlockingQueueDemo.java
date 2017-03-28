package org.noclassdef.example.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class Producer implements Runnable {

	BlockingQueue<Integer> queue;
	private volatile boolean pause = Boolean.FALSE;
	AtomicInteger atomicInt;

	public Producer(BlockingQueue<Integer> queue) {
		this.queue = queue;
		atomicInt = new AtomicInteger();
	}

	public void pause() {
		this.pause = Boolean.TRUE;
		System.out.println("Producer Paused");
	}

	public void resume() {
		this.pause = Boolean.FALSE;
		System.out.println("\nProducer Resume");
	}

	@Override
	public void run() {
		while (true) {

			try {

				if (!pause) {
					this.queue.put(atomicInt.incrementAndGet());
					Thread.sleep(100);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class Consumer implements Runnable {

	BlockingQueue<Integer> queue;

	public Consumer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {

				System.out.println(queue.take());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

public class LinkedBlockingQueueDemo {

	private BlockingQueue<Integer> queue;

	public void demo() {
		this.queue = new LinkedBlockingQueue<>();

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		ExecutorService service = Executors.newFixedThreadPool(2);

		service.submit(consumer);
		service.submit(producer);

		while (true) {

			try {
				Thread.sleep(1000);
				producer.pause();
				Thread.sleep(1000);
				producer.resume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new LinkedBlockingQueueDemo().demo();
	}

}
