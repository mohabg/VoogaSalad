package gameElements;

public class ApplyBehaviorConditions {

	
	private double shootingProbability;
	private int frameDelay;
	private double distFromUser;
	private int framesPassed;
	private double maxDuration;
	private double currentDuration;
	
	public ApplyBehaviorConditions(){
		
	}
	public double getCurrentDuration() {
		return currentDuration;
	}
	public void setCurrentDuration(double currentDuration) {
		this.currentDuration = currentDuration;
	}
	public double getMaxDuration() {
		return maxDuration;
	}
	public void setMaxDuration(double duration) {
		this.maxDuration = duration;
	}
	public double getShootingProbability() {
		return shootingProbability;
	}
	public void setShootingProbability(double shootingProbability) {
		this.shootingProbability = shootingProbability;
	}
	public int getFrameDelay() {
		return frameDelay;
	}
	public void setFrameDelay(int frameDelay) {
		this.frameDelay = frameDelay;
	}
	public double getDistFromUser() {
		return distFromUser;
	}
	public void setDistFromUser(double distFromUser) {
		this.distFromUser = distFromUser;
	}
	public int getFramesPassed() {
		return framesPassed;
	}
	public void setFramesPassed(int framesPassed) {
		this.framesPassed = framesPassed;
	}
}
