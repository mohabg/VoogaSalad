package gameplayer;

import authoringEnvironment.Settings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HUDWindow{
	private HeadsUpDisplay myHUD;
	private FlowPane myFlowPane;

	PlayScreen myPlayScreen;
	
	public HUDWindow(PlayScreen myPlayScreen) {
		this.myPlayScreen =  myPlayScreen;
		myFlowPane = new FlowPane();
		Settings.setHUDWindowSettings(myFlowPane);
		myFlowPane.getStylesheets().add("gameplayer/HUDWindow.css");
		myFlowPane.setId("myFlowPane");
//		createFlowPane();
		
		DoubleProperty HUDHealth = myPlayScreen.getCurrentLevel().getLevelProperties().getUserControlledSprite().getMyHealth().getProperty();
		DoubleProperty HUDTime = myPlayScreen.getCurrentLevel().getLevelProperties().getTime().getMyTime();
		DoubleProperty HUDScore = new SimpleDoubleProperty(myPlayScreen.getCurrentLevel().getLevelProperties().getCurrentPoints());
		DoubleProperty HUDLives = myPlayScreen.getCurrentLevel().getLevelProperties().getUserControlledSprite().getMyHealth().getNumLives();
		DoubleProperty HUDLevelNumber = new SimpleDoubleProperty(myPlayScreen.getCurrentLevel().getLevelProperties().getLevelID());
	
		addHUDElement("Health: ", HUDHealth);
		addHUDElement("Lives: ", HUDLives);
		addHUDElement("Time: ", HUDTime);
		addHUDElement("Score: ", HUDScore);
		addHUDElement("Level Number: ", HUDLevelNumber);
	}
	
	private void addHUDElement(String label, DoubleProperty HUDProperty){
		HBox HUDHbox = new HBox();
		Label HUDKey = new Label(label);
		Label HUDValue = new Label();
		
		StringProperty stringProp = new SimpleStringProperty();
		DoubleProperty doubleProp = HUDProperty;
		stringProp.set(doubleProp.getValue().toString());
		doubleProp.addListener((o, ov, nv) -> {
			stringProp.set(Integer.toString(o.getValue().intValue()));
		});
		HUDValue.textProperty().bindBidirectional(stringProp);
		HUDHbox.getChildren().addAll(HUDKey, HUDValue);
		
		myFlowPane.getChildren().add(HUDHbox);
	}
	
	public Pane getPane(){
		return myFlowPane;
	}
	
	public void initHUD() {
//		myHUD.addToHUDElement(HUDEnum.Up, pauseButton);
		myHUD.addToHUDElement(HUDEnum.Up, myPlayScreen.getTime(), myPlayScreen.getHealth(), myPlayScreen.getScore());
//		myHUD.addToHUDElement(HUDEnum.Up, currentLevel.getCurrentSprite().getHealth().getProperty());
	}
}