package com.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Reverse implements Comparable<Reverse>{

	private String sortAsc;
	private String sortDesc;

	Reverse(String sortAsc, String sortDesc){
		this.sortAsc = sortAsc;
		this.sortDesc = sortDesc;
	}
	
	public String getSortAsc() {
		return sortAsc;
	}

	public void setSortAsc(String sortAsc) {
		this.sortAsc = sortAsc;
	}

	public String getSortDesc() {
		return sortDesc;
	}

	public void setSortDesc(String sortDesc) {
		this.sortDesc = sortDesc;
	}

	@Override
	public int compareTo(Reverse o) {
	
		int result = this.getSortAsc().compareTo(o.getSortAsc());
		
		if(result == 0){
			return this.getSortDesc().compareTo(o.getSortDesc());
		}
		
		return result;
	}
	
	
	public String toString(){
		return sortAsc + " : " + sortDesc;
	}
}

class ReverseComparator implements Comparator<Reverse>{

	@Override
	public int compare(Reverse o1, Reverse o2) {
		int result = o1.getSortAsc().compareTo(o2.getSortAsc());
		
		if(result == 0){
			return -1 * o1.getSortDesc().compareTo(o2.getSortDesc());
		}
		
		return result;
	}
}

class ReverseAllComparator implements Comparator<Reverse>{

	@Override
	public int compare(Reverse o1, Reverse o2) {
		int result = o1.getSortAsc().compareTo(o2.getSortAsc());
		
		if(result == 0){
			result = o1.getSortDesc().compareTo(o2.getSortDesc());
		}
		
		return result * -1;
	}
}


public class CompareDemo {
	
	public static void main(String[] args){
		
		List<Reverse> list = new ArrayList<Reverse>(3);
		list.add(new Reverse("C","11"));
		list.add(new Reverse("D","yy"));
		list.add(new Reverse("C","22"));
		list.add(new Reverse("A","aa"));
		list.add(new Reverse("A","zz"));
		list.add(new Reverse("B","aa"));
		list.add(new Reverse("D","xx"));
		list.add(new Reverse("A","bb"));
		
		Collections.sort(list);
		
		System.out.println("Comparable:compareTo - Should be natural order for both the Strings");
		
		for(Reverse r : list){
			System.out.println(r);
		}
		
		
		Collections.sort(list, new ReverseComparator());
		
		System.out.println("Comparator:compare - Should be asc for first and desc for last string");
		
		for(Reverse r : list){
			System.out.println(r);
		}
		
		Collections.sort(list, new ReverseAllComparator());
		
		System.out.println("Comparator:compare - Should be desc for both the string");
		
		for(Reverse r : list){
			System.out.println(r);
		}

	}
}


