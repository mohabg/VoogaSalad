package itemWindow;

import java.util.ArrayList;

import authoringEnvironment.ViewSprite;
import javafx.scene.control.Tab;

public interface ITab {
	
	public void populateTab(ArrayList<ViewSprite> viewSprites);
	public void setTabTitle(String tabTitle);
	public Tab getTab();
	
}