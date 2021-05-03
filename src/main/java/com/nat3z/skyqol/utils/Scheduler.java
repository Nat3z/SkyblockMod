package com.nat3z.skyqol.utils;

import com.nat3z.skyqol.CheckIfSupporter;

public class Scheduler {
	
	public Runnable[] runs = new Runnable[69420];
	
	public static void runTask(Runnable run, int Time) {
		new Thread(() -> {
			CheckIfSupporter.wait(Time);
			run.run();
		}).start();
	}
	
}
