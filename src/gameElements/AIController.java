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
	private List<ISprite> spritesToSpawn;
	
	public AIController(){
		this(null);
	}
	
	public AIController(SpriteFactory spriteFactory){
		spritesToSpawn = new ArrayList<>();
		this.spriteFactory = spriteFactory;
		for(ISprite sprite : this.getSpriteFactory().getSpriteMap().getSprites()){
			if(!sprite.equals(this.spriteFactory.getSpriteMap().getUserControlledSprite())){
				spritesToSpawn.add(sprite);
			}
		}
	}
	
	public void update(){
		for(ISprite sprite : this.spritesToSpawn){
			this.accept(sprite.getSpawnConditions());
		}
	}
	public void accept(ExecuteConditions conditions){
		conditions.visit(this);
	}
	public void setSpriteFactory(SpriteFactory spriteFactory) {
		this.spriteFactory = spriteFactory;
	}

	public SpriteFactory getSpriteFactory() {
		return this.spriteFactory;
	}

	public List<ISprite> getSpritesToSpawn() {
		return spritesToSpawn;
	}

	public void setSpritesToSpawn(List<ISprite> spritesToSpawn) {
		this.spritesToSpawn = spritesToSpawn;
	}

	
}
