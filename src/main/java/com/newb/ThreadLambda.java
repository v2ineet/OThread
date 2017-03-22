package com.newb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadLambda {

	public static void main(String[] args) {
		
		List<Integer> arrayList = new ArrayList<>();
		arrayList.add(105);
		arrayList.add(101);
		arrayList.add(106);
		arrayList.add(103);
	
		System.out.println("Before Sort");
		arrayList.forEach(x -> print(x));
		
		Collections.sort(arrayList, (x1, x2) -> x2.compareTo(x1));
		
		System.out.println("\n\nAfter Reverse Sort");
		arrayList.forEach(x -> print(x));


		Runnable task = () -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("hello");
			}
		};

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("hello 1");
			}
		}).start();

		new Thread(task).start();

	}

	private static void print(Integer x) {
		// TODO Auto-generated method stub
		System.out.println("Do Anything " + x);

	}

}
