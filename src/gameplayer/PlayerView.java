package gameplayer;

import java.util.List;
import java.util.Map;

import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import authoringEnvironment.ViewSprite;
import highscoretable.HighScoreController;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import resources.FrontEndData;

public class PlayerView extends Screen {
	private HeadsUpDisplay myHUD;
	private Map<Integer, ViewSprite> myViewSprites;

	public PlayerView(){
		super();
		myHUD = new HeadsUpDisplay(getScene().getWidth(), getScene().getHeight());

	}

	private void initHUD() {
//		myHUD.addToHUDElement(HUDEnum.Up, pauseButton);
//		//myHUD.addToHUDElement(HUDEnum.Up, myEngine.getGameTimeInSeconds(), myEngine.getCurrentLevel().getScore());
//		myHUD.addToHUDElement(HUDEnum.Up, currentLevel.getCurrentSprite().getHealth().getProperty());
//		myPane.getChildren().add(myHUD.getHUD());
	}
	public void setSprites(List<Integer> activeSprites) {
		// System.out.println("printing setsprites"+ currentLevel.getSpriteMap().getSpriteMap().size());
		myPane.getChildren().removeAll(myViewSprites.values());
		activeSprites.stream().forEach(s->{
			myPane.getChildren().addAll(myViewSprites.get(s));
		});
	}
	
	public Pane getPane() {
		return myPane;
	}
	
	public void setBackground(String background){
		myPane.setStyle("-fx-background-image: url(" + background + ");" + "\n" +
				   "-fx-background-repeat: repeat;");	
		
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
	
	
}
