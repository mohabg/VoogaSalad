package gameElements;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import gameplayer.SpriteFactory;

@IgnoreField
public class SpawnController {
	
	private SpriteFactory spriteFactory;
	
	public SpawnController(SpriteFactory spriteFactory){
		this.spriteFactory = spriteFactory;
	}
	
	public void update(){
		for(ISprite sprite: spriteFactory.getSpriteMap().getSprites()){
			this.accept(sprite.getSpawnConditions());
		}
	}
	public void accept(SpawnConditions conditions){
		conditions.visit(this);
	}

	public SpriteFactory getSpriteFactory() {
		return spriteFactory;
	}
}
