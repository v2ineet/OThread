package com.newb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SharedPipeLine{
	
	private int lineValue;
	private boolean lineFull;
	int nextLine = 1;
	
	public void pushToLine1(int value){
		
		synchronized(this){
			while(lineFull || nextLine != 1){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.lineValue  = value;		
			nextLine = 2;
			lineFull = Boolean.TRUE;
			notifyAll();
		}
	}
	
	public void pushToLine2(int value){
		
		synchronized(this){
			while(lineFull || nextLine != 2){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.lineValue  = value;		
			nextLine = 3;
			lineFull = Boolean.TRUE;
			notifyAll();
		}
	}

	public void pushToLine3(int value){
		
		synchronized(this){
			while(lineFull || nextLine != 3){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.lineValue  = value;		
			nextLine = 1;
			lineFull = Boolean.TRUE;
			notifyAll();
		}
	}
	
	
	
	public int getLineValue(){
		synchronized(this){
			while(!lineFull){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			

			lineFull = Boolean.FALSE;
			notifyAll();
			
			return lineValue;

		}	
	}
}

class ProducerLine1 implements Runnable{
	
	private SharedPipeLine line;
	
	public ProducerLine1(SharedPipeLine line){
		this.line = line;
	}

	@Override
	public void run() {
		for(int i = 1; i <= 99; i+=3){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			line.pushToLine1(i);
		}
	}
}


class ProducerLine2 implements Runnable{
	
	private SharedPipeLine line;
	
	public ProducerLine2(SharedPipeLine line){
		this.line = line;
	}	

	@Override
	public void run() {
		for(int i = 2; i <= 99; i+=3){
			line.pushToLine2(i);
		}
	}
}

class ProducerLine3 implements Runnable{
	
	private SharedPipeLine line;
	
	public ProducerLine3(SharedPipeLine line){
		this.line = line;
	}
	
	@Override
	public void run() {
		for(int i = 3; i <= 99; i+=3){
			line.pushToLine3(i);
		}
	}
	
}


class ConsumeLine implements Runnable{

	private SharedPipeLine line;
	
	public ConsumeLine(SharedPipeLine line){
		this.line = line;
	}	
	
	@Override
	public void run() {
		while(true){
			System.out.println(line.getLineValue());
		}	
	}
}


public class ThreeThreadNew {
	
	
	public static void main(String[] args){
		
		 SharedPipeLine line = new SharedPipeLine();
		 ExecutorService executorService = Executors.newFixedThreadPool(4);
		 
		 executorService.submit(new ProducerLine1(line));
		 executorService.submit(new ProducerLine2(line));
		 executorService.submit(new ProducerLine3(line));
		 executorService.submit(new ConsumeLine(line));
		 
		 
		 executorService.shutdown();
		 
		 try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
