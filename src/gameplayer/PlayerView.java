package gameplayer;

import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import authoringEnvironment.ViewSprite;
import highscoretable.HighScoreController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerView extends Screen {
	private Map<Integer, Map<Integer, ViewSprite>> myViewSpriteMap;
	private Map<Integer, ViewSprite> myViewSprites;
	private Map<Integer, StringProperty> myBackgrounds;

	public PlayerView(){
		super();
		myViewSpriteMap = new HashMap<Integer, Map<Integer, ViewSprite>>();
		myViewSprites = new HashMap<Integer, ViewSprite>();
		myBackgrounds = new HashMap<Integer, StringProperty>();
		//TODO: find better way
		myPane.getChildren().add(new Button("a"));
	}

	
	public void setSprites(List<Integer> activeSprites) {
		// System.out.println("printing setsprites"+ currentLevel.getSpriteMap().getSpriteMap().size());
		//myPane.getChildren().removeAll(myViewSprites.values());
		this.clearSprites();
		activeSprites.stream().forEach(s->{
			ViewSprite vs = myViewSprites.get(s);
			if (vs != null) {
				System.out.println(vs.getMyImage() + " x: " + vs.getX() + " " + " y: " + vs.getY());
				myPane.getChildren().add(0, vs);
				//myPane.getChildren().add(vs);
			}
		});
	}
	
//	public Pane getPane() {
//		return myPane;
//	}
	public void setBackgroundList(Integer id, StringProperty background){
		myBackgrounds.put(id,  background);
	}
	public void setBackground(Integer id){
		StringProperty backgroundName = myBackgrounds.get(id);
		if (backgroundName.getValue() != "") {
			myPane.setBackground(new Background(new BackgroundImage(new Image(backgroundName.get()), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));			
		} else {
		//myPane.setBackground(new Background(new BackgroundImage(new Image(backgroundName.get()), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		}
	}
	
	public Map<Integer, ViewSprite> getViewSprites() {
		return myViewSprites;
	}
	public void makeHighScoreTable(){
		HighScoreController hsc = new HighScoreController();
//		hsc.addHighScore(myEngine.getScore(), myEngine.getName());
		hsc.addHighScore(10.1, "game");
		myPane.getChildren().clear();
		myPane.getChildren().add(hsc.getTable());
		
	}

	public void setViewSprites(int id, Map<Integer, ViewSprite> levelSprites) {
		myViewSpriteMap.put(id, levelSprites);
//		myViewSprites = levelSprites;
	}

	public void setLevelSprites(Integer levelID) {
		myViewSprites = myViewSpriteMap.get(levelID);
		
	}

	public void clearSprites() {
		myPane.getChildren().removeAll(myViewSprites.values());
	}
	
	
}
