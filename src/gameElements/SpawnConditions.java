package gameElements;

import authoringEnvironment.Settings;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.*;
import java.awt.*;
import java.util.List;
/**
 * 
 * @author mohabgabal
 *
 */
public class SpawnConditions extends ExecuteConditions{

	private double MY_SCREEN_RANGE =  0.4 * Settings.getScreenWidth();
    private IntegerProperty numberToSpawn;
    @IgnoreField
    private ISprite spriteToSpawn;
    private List<SpawnPoint> spawnPoints;
    private DoubleProperty spawnOnTopProbability;
    private DoubleProperty spawnOnLeftProbability;
    private DoubleProperty spawnOnRightProbability;
    private DoubleProperty spawnOnBottomProbability;
    
	public SpawnConditions(){
        super();
        this.numberToSpawn = new SimpleIntegerProperty(0);
        this.spawnPoints = new ArrayList<>();
        this.spawnOnTopProbability = new SimpleDoubleProperty(0);
        this.spawnOnBottomProbability = new SimpleDoubleProperty(0);
        this.spawnOnLeftProbability = new SimpleDoubleProperty(0);
        this.spawnOnRightProbability = new SimpleDoubleProperty(0);
	}
	
    private Point getSpawnPosition(){
    	SpawnPoint pointToSpawn = spawnPoints.get(findEdgeToSpawn());
    	return new Point(pointToSpawn.getX(), pointToSpawn.getY());
    }

	private int findEdgeToSpawn() {
		double sum = 0;
		for(SpawnPoint point : this.spawnPoints){
			sum += point.getProbability();
		}
    	int index = 0;
    	double side = 0;
    	while(side < Math.random() * sum){
    		side += spawnPoints.get(index++).getProbability();
    	}
		return Math.max(0, index - 1);
	}
   
	public void visit(SpawnController aiController) {
		if(this.isAIReady()){
           for(int i = 0; i < this.numberToSpawn.get(); i++){
                spawnSprite(aiController, spriteToSpawn, this.getSpawnPosition());
               }
			}
		}
	
	protected void spawnSprite(SpawnController aiController, ISprite sprite, Point point) {
            ISprite cloned = aiController.getSpriteFactory().clone(sprite);
            cloned.getSpriteProperties().setX(point.getX());
            cloned.getSpriteProperties().setY(point.getY());
    }
    public int getNumberToSpawn() {
        return numberToSpawn.get();
    }

    public IntegerProperty numberToSpawnProperty() {
        return numberToSpawn;
    }

    public void setNumberToSpawn(int numberToSpawn) {
        this.numberToSpawn.set(numberToSpawn);
    }
	public void setSpriteToSpawn(ISprite spriteToSpawn) {
		this.spriteToSpawn = spriteToSpawn;
	}

}