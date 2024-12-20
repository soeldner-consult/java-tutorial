package com.example.instances;

import java.time.LocalDateTime;

public class CountMyInstances {

	private static int instances = 0;
	private static CountMyInstances lastInstance;
	private static final int MAX_INSTANCES;
	
	static {
		if (LocalDateTime.now().getHour() >= 9 && LocalDateTime.now().getHour() <= 12) {
			MAX_INSTANCES = 10;
		} else {
			MAX_INSTANCES = 5;
		}
	}
		
	private CountMyInstances() {
		instances++;
		lastInstance = this;
	}
	
	public static CountMyInstances getInstance() {
		if (instances >= MAX_INSTANCES) {
			return lastInstance;
		} else {
			return new CountMyInstances();
		}
	}
	
	public static int getInstances() {
		return instances;
	}
	
	public static CountMyInstances getLastInstance() {
		return lastInstance;
	}
	
}
