package gameElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import behaviors.Movement;
import gameplayer.SpriteFactory;

public class AIController {
	
	private SpriteFactory spriteFactory;
	private Map<ExecuteConditions, List<IEnemy>> spawnConditionsForSprites;
	private Map<String, Movement> enemyMovements;
	
	public AIController(SpriteFactory spriteFactory){
		spawnConditionsForSprites = new HashMap<>();
		enemyMovements = new HashMap<String, Movement>();
		this.spriteFactory = spriteFactory;
	}
	
	public void update(){
		for(ExecuteConditions spawnCondition : spawnConditionsForSprites.keySet()){
			List<IEnemy> spritesForCondition = spawnConditionsForSprites.get(spawnCondition);
			if(spawnCondition.isAIReady()){
				for(IEnemy enemy : spritesForCondition){
					IEnemy cloned = enemy.clone();
					spriteFactory.getSpriteMap().addSprite(cloned);
				}
				
			}	
		}
	}

	public void setSpriteFactory(SpriteFactory spriteFactory) {
		this.spriteFactory = spriteFactory;
	}

	
}
