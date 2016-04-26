package authoringEnvironment.authoringToolbar;

import authoringEnvironment.MainAuthoringWindow;
import authoringEnvironment.Project1;
import gameplayer.MainPlayingWindow;
import javafx.scene.control.MenuBar;

public class LEMenuBarCreator extends AbstractMenuBar{

	public LEMenuBarCreator(String gameName) {
		super(gameName);
	}
	
	public void initMenuBar(MainAuthoringWindow mainAuthoringWindow, Project1 window) {
		MenuBarElement myBackMenu = getBackMenu(mainAuthoringWindow);
		MenuBarElement myPlayToggleMenu = getPlayToggle(mainAuthoringWindow, window);
		myMenuBar.getStylesheets().add("authoringEnvironment/authoringToolbar/authoringToolbar.css");
		myMenuBar.getMenus().addAll(myBackMenu.getMenu(), myPlayToggleMenu.getMenu());
	
	}
	private MenuBarElement getPlayToggle(MainAuthoringWindow mainAuthoringWindow, Project1 le){
    	MenuBarElement playToggleButton = new MenuBarElement();
    	playToggleButton.setName("play/edit");
    	playToggleButton.setNewAction("play", e -> {
    		MainPlayingWindow myMainPlayingWindow = new MainPlayingWindow(mainAuthoringWindow, myName, le.getPlayScreen());
    		mainAuthoringWindow.switchScene(myMainPlayingWindow);
    	});
    	return playToggleButton;
    }

}
