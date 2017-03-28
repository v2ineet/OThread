package com.base.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
//https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html#add-E-

public class ArrayLitsRemove {
	
	
	public static void main(String[] args){
		
		
		List<String> list = new ArrayList<>(); 
		
		list.add("itme1");
		list.add("itme2");
		list.add("itme3");
		list.add("itme4");
		
		ListIterator<String> listItr = list.listIterator();
		
		//listItr.remove();// IllegalStateException
		
		System.out.println(list);
		System.out.println("calling next() and remove()\n");
		listItr.next();
		listItr.remove();// Ok After Call to Next
		
		System.out.println(list);
		System.out.println("After Remove -nextIndex:" + listItr.nextIndex());  
		System.out.println("");

		System.out.println("calling add(\"item0\")\n");
		listItr.add("itme0"); //OK
		
		System.out.println(list);
		System.out.println("After Add -nextIndex: " + listItr.nextIndex());  
		System.out.println("");
		
		
		System.out.println("next()" + listItr.next());
		
		//listItr.remove();// IllegalStateException as called after add

		System.out.println("calling next() and than remove()\n");

		listItr.next();
		listItr.remove();
		
		System.out.println(list);
		System.out.println("After Remove -nextIndex: " + listItr.nextIndex());  
		System.out.println("");

		//listItr.remove();  //Illegal state exception - should be called only after next or previous

		System.out.println("calling previous() and than remove()\n");
	
		listItr.previous();
		listItr.remove();
		
		System.out.println(list);
		System.out.println("After Remove -nextIndex: " + listItr.nextIndex());  
		System.out.println("");
		
	}

}



