package com.newb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
	
	
	private final AtomicReference<TradeVolume> tradeVol = new AtomicReference<>(new TradeVolume(0,0));
	
	private class TradeVolume{
		
		private final long quantity;
		private final double price;
		
		private TradeVolume(long quantity, double price){
			this.quantity = quantity;
			this.price = price;
		}
		
		public long getQuantity() {
			return quantity;
		}

		public double getPrice() {
			return price;
		}
	}
	
	public void update(long quantity, double price){
		
		
		/*Following commented code is as per JDK 7*/
		/*while(true){    
			TradeVolume tradeVolume = tradeVol.get();
			TradeVolume updatedtradeVolume = new TradeVolume(tradeVolume.getQuantity() + quantity, tradeVolume.getPrice() + price );
			
			if(tradeVol.compareAndSet(tradeVolume, updatedtradeVolume)){
				return;
			}
		}*/
		
		//following is specific to java 8
		tradeVol.updateAndGet(x -> new TradeVolume(quantity+ x.getQuantity(), price + x.getPrice()));
	}
	
	
	public static void main(String[] args){
	}
}
	
	


