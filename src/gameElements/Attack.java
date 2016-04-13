package gameElements;

import java.util.Random;

public abstract class Attack extends Sprite implements Behavior {

	private Movement movement;
	private Collision collision;
	private int ammunition;
	private int chargeTime;
	private ApplyBehaviorConditions behaviorConditions;
	private boolean readyToShoot;
	private double probability;
	private int frameDelay;
	private double distFromUser;
	private int framesPassed;

	public boolean readyToShoot() {
		if (frameDelay > 0 && probability > 0) {
			if (framesPassed >= frameDelay) {
				if (Math.random() < probability) {
					return true;

				}
			}
		}
		return false;
	}

	public int getFramesPassed() {
		return framesPassed;
	}

	public void setFramesPassed(int framesPassed) {
		this.framesPassed = framesPassed;
	}

	public double getShootingProbability() {
		return probability;
	}

	public void setShootingProbability(double probability) {
		this.probability = probability;
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

	public int getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(int chargeTime) {
		this.chargeTime = chargeTime;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public Collision getCollision() {
		return collision;
	}

	public void setCollision(Collision collision) {
		this.collision = collision;
	}

	public int getAmmunition() {
		return ammunition;
	}

	public void setAmmunition(int ammunition) {
		this.ammunition = ammunition;
	}

	public boolean hasAmmunitionLeft() {
		return ammunition != 0;
	}

	public boolean isReadyToShoot() {
		return readyToShoot;
	}

	public void setReadyToShoot(boolean readyToShoot) {
		this.readyToShoot = readyToShoot;
	}
}