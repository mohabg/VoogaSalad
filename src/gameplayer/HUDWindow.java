package gameplayer;

import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import javafx.scene.layout.Pane;

public class HUDWindow{
	private HeadsUpDisplay myHUD;

	PlayScreen myPlayScreen;
	
	public HUDWindow(PlayScreen myPlayScreen) {
		this.myPlayScreen =  myPlayScreen;
		myHUD = new HeadsUpDisplay();

	}
	
	
	public Pane getPane(){
		return myHUD.getHUD();
	}
	
	public void initHUD() {
//		myHUD.addToHUDElement(HUDEnum.Up, pauseButton);
		myHUD.addToHUDElement(HUDEnum.Up, myPlayScreen.getTime(), myPlayScreen.getHealth(), myPlayScreen.getScore());
//		myHUD.addToHUDElement(HUDEnum.Up, currentLevel.getCurrentSprite().getHealth().getProperty());
	}
}