package com.newb;

import java.util.ArrayList;
import java.util.List;

public class ThreadLambda {

	public static void main(String[] args) {
		
		List<Integer> ArrayList = new ArrayList<>();
		ArrayList.add(100);
		ArrayList.add(101);
		ArrayList.add(102);
		ArrayList.add(103);

		ArrayList.forEach(x -> print(x));

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
