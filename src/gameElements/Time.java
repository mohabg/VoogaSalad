package gameElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;


public class Time {
	
	private int myDelay;
	private Timer myTimer;
	private int myInitialTime;

	public Time() {
		myDelay = 1000; //in milliseconds --> 1 second
		myTimer = new Timer();
		myInitialTime = 0;
	}
	
	public Time(int initialTime) {
		myDelay = 1000; //in milliseconds --> 1 second
		myTimer = new Timer();
		myInitialTime = initialTime;
	}
	
	public void startTime() {
		if ( myInitialTime == 0)
			runRegularTimer();
		else
			runCountdownTimer(myInitialTime);
	}
	
	private void runCountdownTimer(int initialTime) {
		CountdownTimerTask cdTimerTask = new CountdownTimerTask(initialTime);
		myTimer.schedule(cdTimerTask, 0, myDelay);
	}

	private void runRegularTimer() {
		TimerTask task = new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				count++;
				int min = count/60;
			    int sec = count%60;
				System.out.println("Time: " + min + ":" + sec);
			}
		};
		myTimer.schedule(task, myDelay);
	}
	
	public void pauseTime() {
		//TODO: look into pausing and resuming TimerTasks
		myTimer.cancel();
	}
	
	public void resetTime() {
		myTimer.cancel();
		myTimer = new Timer();
		startTime();
	}
	
	public void setScore() {
		//TODO: once score is implemented, update here
	}
	
}
