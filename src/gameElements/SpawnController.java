package gameElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import behaviors.Movement;
import gameplayer.SpriteFactory;

@IgnoreField
public class SpawnController {
	
	private SpriteFactory spriteFactory;
	
	public SpawnController(SpriteFactory spriteFactory){
		this.spriteFactory = spriteFactory;
	}
	
	public void update(){
		Iterator<ISprite> spriteIt = this.spriteFactory.getSpriteMap().getSprites().iterator();
		while(spriteIt.hasNext()){
			this.accept(spriteIt.next().getSpawnConditions());
		}
	}
	public void accept(SpawnConditions conditions){
		conditions.visit(this);
	}
	public void setSpriteFactory(SpriteFactory spriteFactory) {
		this.spriteFactory = spriteFactory;
	}

	public SpriteFactory getSpriteFactory() {
		return this.spriteFactory;
	}

	
}
