package gameElements;

import java.util.List;

import gameplayer.SpriteFactory;

public class AIController {
	
	private SpriteFactory spriteFactory;
	private ExecuteConditions spawnConditions;
	private List<IEnemy> enemiesToSpawn;
	
	public AIController(){
		spawnConditions = new ExecuteConditions();
	}
	
	public void update(){
		if(spawnConditions.isAIReady()){
			
		}
	}
	
	public SpriteFactory getSpriteFactory() {
		return spriteFactory;
	}

	public void setSpriteFactory(SpriteFactory spriteFactory) {
		this.spriteFactory = spriteFactory;
	}

	public double getMinDistFromUser() {
		return spawnConditions.getMinDistFromUser();
	}

	public double getMaxDistFromUser() {
		return spawnConditions.getMaxDistFromUser();
	}

	public double getCurrentDuration() {
		return spawnConditions.getCurrentDuration();
	}

	public void setCurrentDuration(double currentDuration) {
		spawnConditions.setCurrentDuration(currentDuration);
	}

	public double getMaxDuration() {
		return spawnConditions.getMaxDuration();
	}

	public void setMaxDuration(double duration) {
		spawnConditions.setMaxDuration(duration);
	}

	public double getProbability() {
		return spawnConditions.getProbability();
	}

	public void setProbability(double probability) {
		spawnConditions.setProbability(probability);
	}

	public double getDistFromUser() {
		return spawnConditions.getDistFromUser();
	}

	public void setDistFromUser(double distFromUser) {
		spawnConditions.setDistFromUser(distFromUser);
	}

	public void setFrequency(double frequency) {
		spawnConditions.setFrequency(frequency);
	}

	public double getFrequency() {
		return spawnConditions.getFrequency();
	}
	
}
