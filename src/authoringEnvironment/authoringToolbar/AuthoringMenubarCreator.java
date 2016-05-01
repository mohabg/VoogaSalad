package authoringEnvironment.authoringToolbar;

import authoringEnvironment.MainAuthoringWindow;
import gameplayer.GameLoader;
import gameplayer.MainPlayingWindow;
import interfaces.ITabPane;
import resources.FrontEndData;

/**
 * This is the creator for the menubar, which allows the user to select new
 * files, add a level, save, or return to the start screen
 * 
 * @author Huijia, David Yan, Joe Jacob
 *
 */
public class AuthoringMenubarCreator extends AbstractMenuBar{

	private String myGameName;

	public AuthoringMenubarCreator(String gameName) {
		super(gameName);
		myGameName = gameName;
	}

	/**
	 * This method initializes the menubar using the tabpane (to set the add tab
	 * action). We made this to eliminate dependencies.
	 * 
	 * @param mainAuthoringWindow
	 * 
	 * @param window
	 */
	public void initMenuBar(MainAuthoringWindow mainAuthoringWindow, ITabPane window) {
		MenuBarElement myBackMenu = getBackMenu(mainAuthoringWindow);
		MenuBarElement myFileMenuMaker = getFileMenu(mainAuthoringWindow, window);
		MenuBarElement myGameMenu = getGameMenu(window);
		MenuBarElement myPlayToggleMenu = getPlayToggle(mainAuthoringWindow, window);
		myMenuBar.getStylesheets().add(FrontEndData.TAB_STYLESHEET);
		myMenuBar.getMenus().addAll(myBackMenu.getMenu(), myFileMenuMaker.getMenu(), myGameMenu.getMenu(), myPlayToggleMenu.getMenu());
	}
	
    private MenuBarElement getFileMenu(MainAuthoringWindow mainAuthoringWindow, ITabPane window) {
    	MenuBarElement myFileMenuMaker = new MenuBarElement();
        myFileMenuMaker.setName(FrontEndData.ButtonLabels.getString("FileMenu"));
        myFileMenuMaker.setNewAction(FrontEndData.ButtonLabels.getString("FileMenu1"), e -> {
            mainAuthoringWindow.reset();
        });
        myFileMenuMaker.setNewAction(FrontEndData.ButtonLabels.getString("SaveGameMenu1"), e -> {
        	GameLoader.saveGame(myName, window);
        });
        
        return myFileMenuMaker;
    }
    
    private MenuBarElement getGameMenu(ITabPane window){
    	MenuBarElement myGameMenu = new MenuBarElement();
    	myGameMenu.setName(FrontEndData.ButtonLabels.getString("GameMenu"));
//    	myGameMenu.setNewAction(FrontEndData.ButtonLabels.getString("PlayMenu1"), e -> {
//             playMyGame(window);
//        });
    	myGameMenu.setNewAction(FrontEndData.ButtonLabels.getString("AddNewLevelMenu1"), e -> {
            window.addNewTab();
        });
    	return myGameMenu;
    }
    
    private MenuBarElement getPlayToggle(MainAuthoringWindow mainAuthoringWindow, ITabPane tabLevels){
    	MenuBarElement playToggleButton = new MenuBarElement();
    	playToggleButton.setName(FrontEndData.ButtonLabels.getString("Play/Edit"));
    	playToggleButton.setNewAction(FrontEndData.ButtonLabels.getString("PlayOption"), e -> {
    		GameLoader.saveGame(myName, tabLevels);
    		MainPlayingWindow myMainPlayingWindow = new MainPlayingWindow(mainAuthoringWindow, myName);
    		mainAuthoringWindow.switchScene(myMainPlayingWindow);
    	});
    	return playToggleButton;
    }
    
//	private void playMyGame(ITabPane tabLevels) {
//		GameLoader.saveGame(myName, tabLevels);
//		Stage myStage = (Stage) myMenuBar.getScene().getWindow();
//		myStage.setScene(new PlayScreen(myName).getScreen().getScene());
//		myStage.centerOnScreen();
//	}

	

}