package gameElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import behaviors.Movement;
import gameplayer.SpriteFactory;

@IgnoreField
public class AIController {
	
	private SpriteFactory spriteFactory;
	private Map<ExecuteConditions, List<ISprite>> executeConditionToSprites;
	private Map<String, Movement> enemyMovements;
	
	public AIController(){
		this(null);
	}
	
	public AIController(SpriteFactory spriteFactory){
		executeConditionToSprites = new HashMap<>();
		enemyMovements = new HashMap<String, Movement>();
		this.spriteFactory = spriteFactory;
		Map<ExecuteConditions, List<ISprite>> conditions = this.getExecuteConditionToSprites();
		ExecuteConditions spawn = new EnemySpawnConditions();
		List<ISprite> sprites = new ArrayList<>();
		for(ISprite sprite : this.getSpriteFactory().getSpriteMap().getSprites()){
			sprites.add(sprite);
		}
		conditions.put(spawn, sprites);
	}
	
	public void update(){
		for(ExecuteConditions executeCondition : executeConditionToSprites.keySet()){
				this.accept(executeCondition);
		}
	}
	public void accept(ExecuteConditions conditions){
		conditions.visit(this);
	}
	public void setSpriteFactory(SpriteFactory spriteFactory) {
		this.spriteFactory = spriteFactory;
	}

	public Map<ExecuteConditions, List<ISprite>> getExecuteConditionToSprites() {
		return executeConditionToSprites;
	}

	public void setExecuteConditionToSprites(Map<ExecuteConditions, List<ISprite>> spawnConditionsForSprites) {
		this.executeConditionToSprites = spawnConditionsForSprites;
	}

	public Map<String, Movement> getEnemyMovements() {
		return enemyMovements;
	}

	public void setEnemyMovements(Map<String, Movement> enemyMovements) {
		this.enemyMovements = enemyMovements;
	}

	public SpriteFactory getSpriteFactory() {
		return this.spriteFactory;
	}

	
}
