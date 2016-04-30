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
	
	public AIController(){
		this(null);
	}
	
	public AIController(SpriteFactory spriteFactory){
		executeConditionToSprites = new HashMap<>();
		this.spriteFactory = spriteFactory;
		Map<ExecuteConditions, List<ISprite>> conditions = this.getExecuteConditionToSprites();
		ExecuteConditions spawn = new EnemySpawnConditions();
		List<ISprite> sprites = new ArrayList<>();
		for(ISprite sprite : this.getSpriteFactory().getSpriteMap().getSprites()){
			if(!sprite.equals(this.spriteFactory.getSpriteMap().getUserControlledSprite())){
				sprites.add(sprite);
			}
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
	
	public SpriteFactory getSpriteFactory() {
		return this.spriteFactory;
	}

	
}
