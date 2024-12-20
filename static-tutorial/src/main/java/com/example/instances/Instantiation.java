package com.example.instances;

public class Instantiation {
	public static void main(String[] args) {
		CountMyInstances instance1 = CountMyInstances.getInstance();
		System.out.println(CountMyInstances.getLastInstance());
		CountMyInstances instance2 = CountMyInstances.getInstance();
		CountMyInstances instance3 = CountMyInstances.getInstance();
		CountMyInstances instance4 = CountMyInstances.getInstance();
		CountMyInstances instance5 = CountMyInstances.getInstance();
		System.out.println(CountMyInstances.getLastInstance());
		CountMyInstances instance6 = CountMyInstances.getInstance();
		System.out.println(CountMyInstances.getLastInstance());
		System.out.println(CountMyInstances.getInstances());
		
		try {
			throwsCNSException();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		
	}
	
	public static void throwsCNSException() throws CloneNotSupportedException {
		Instantiation dc = new Instantiation();
	  	dc.clone();
	}
}

