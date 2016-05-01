package gameElements;

import authoringEnvironment.Settings;
import javafx.beans.property.IntegerProperty;
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

	public SpawnConditions(){
        super();
        this.numberToSpawn = new SimpleIntegerProperty(0);
	}
	private List<Point>  getSpawnPositions(){
        List<Point> myPoints = new ArrayList<>();
        for(int i = 0; i < numberToSpawn.getValue(); i++) {
            int newX = (int) (Math.random() * MY_SCREEN_RANGE);
            myPoints.add(new Point(newX, 0));
        }
		return myPoints;
	}

    private List<Point> getSpawnPositions(int myTotalScreenHeight){
        List<Point> myPoints = new ArrayList<>();
        int myCurrentScreenY = 0;
        for(int i = 0; i < numberToSpawn.get(); i++){
            int myX =  (int) (Math.random() * MY_SCREEN_RANGE);
            Point myTempPoint = new Point(myX, myCurrentScreenY);
            myPoints.add(myTempPoint);
        }
        return myPoints;
    }

	@Override
	public void visit(SpawnController aiController) {
		if(this.isAIReady()){
            for(ISprite sprite : aiController.getSpritesToSpawn()){
                if(sprite.getSpriteProperties().canMove()){
                	List<Point> points = this.getSpawnPositions();
                	for(int i = 0; i < this.numberToSpawn.get(); i++){
                		spawnSprite(aiController, sprite, points.get(i));
                	}
                }
                else{
                	List<Point> points = this.getSpawnPositions(Settings.getScreenHeight());
                	for(int i = 0; i < this.numberToSpawn.get(); i++){
                		spawnSprite(aiController, sprite, points.get(i));
                	}
                }
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

}