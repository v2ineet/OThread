package com.base;

import java.util.ArrayList;
import java.util.Collections;
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
			return -1 * this.getSortDesc().compareTo(o.getSortDesc());
		}
		
		return result;
	}
	
	
	public String toString(){
		return sortAsc + " : " + sortDesc;
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
		
		for(Reverse r : list){
			System.out.println(r);
		}
	}
}
