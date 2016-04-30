package authoringEnvironment.authoringToolbar;

import authoringEnvironment.MainAuthoringWindow;
import authoringEnvironment.LiveEditing;
import gameplayer.MainPlayingWindow;
import resources.FrontEndData;
/**
 * 
 * @author davidyan, Huijia Yu, Joe Jacob
 * Creates the MenuBar for use in both the regular Authoring Environment in addition to Live Editing
 *
 */
public class LEMenuBarCreator extends AbstractMenuBar{

	public LEMenuBarCreator(String gameName) {
		super(gameName);
	}
	
	public void initMenuBar(MainAuthoringWindow mainAuthoringWindow, LiveEditing window) {
		MenuBarElement myBackMenu = getBackMenu(mainAuthoringWindow);
		MenuBarElement myPlayToggleMenu = getPlayToggle(mainAuthoringWindow, window);
		myMenuBar.getStylesheets().add(FrontEndData.TAB_STYLESHEET);
		myMenuBar.getMenus().addAll(myBackMenu.getMenu(), myPlayToggleMenu.getMenu());
	
	}
	private MenuBarElement getPlayToggle(MainAuthoringWindow mainAuthoringWindow, LiveEditing le){
    	MenuBarElement playToggleButton = new MenuBarElement();
    	playToggleButton.setName(FrontEndData.ButtonLabels.getString("Play/Edit"));
    	playToggleButton.setNewAction(FrontEndData.ButtonLabels.getString("PlayOption"), e -> {
    		MainPlayingWindow myMainPlayingWindow = new MainPlayingWindow(mainAuthoringWindow, myName, le.getPlayScreen());
    		mainAuthoringWindow.switchScene(myMainPlayingWindow);
    	});
    	return playToggleButton;
    }

}
