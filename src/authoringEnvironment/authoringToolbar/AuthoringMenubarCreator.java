package authoringEnvironment.authoringToolbar;

import authoringEnvironment.MainAuthoringWindow;
import gameplayer.GameLoader;
import gameplayer.GamePlayingFileScreen;
import gameplayer.MainPlayingWindow;
import interfaces.ITabPane;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import resources.FrontEndData;

/**
 * This is the creator for the menubar, which allows the user to select new
 * files, add a level, save, or return to the start screen
 * 
 * @author Huijia, David Yan, Joe Jacob
 *
 */
public class AuthoringMenubarCreator {
	private MenuBar myMenuBar;
	private String myName;

	// TODO SWITCH TO REFLECTION


	public AuthoringMenubarCreator(String gameName) {
		myMenuBar = new MenuBar();
		myName = gameName;
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
		MenuBarElement myFileMenuMaker = getFileMenu(window);
		MenuBarElement myGameMenu = getGameMenu(window);
		MenuBarElement myPlayToggleMenu = getPlayToggle(mainAuthoringWindow);
		myMenuBar.getStylesheets().add("authoringEnvironment/authoringToolbar/authoringToolbar.css");
		myMenuBar.getMenus().addAll(myBackMenu.getMenu(), myFileMenuMaker.getMenu(), myGameMenu.getMenu(), myPlayToggleMenu.getMenu());
	}

    private MenuBarElement getBackMenu(MainAuthoringWindow mainAuthoringWindow) {
    	MenuBarElement myBackMenu = new MenuBarElement();
        myBackMenu.setName(FrontEndData.ButtonLabels.getString("BackMenu"));
        myBackMenu.setNewAction(FrontEndData.ButtonLabels.getString("BackMenu1"), e -> {
            mainAuthoringWindow.returnToParentScreen();
        });
        return myBackMenu;
    }

    private MenuBarElement getFileMenu(ITabPane window) {
    	MenuBarElement myFileMenuMaker = new MenuBarElement();
        myFileMenuMaker.setName(FrontEndData.ButtonLabels.getString("FileMenu"));
        myFileMenuMaker.setNewAction(FrontEndData.ButtonLabels.getString("FileMenu1"), e -> {
            System.out.println("NOT IMPLEMENTED");
        });
//        myFileMenuMaker.setNewAction(FrontEndData.ButtonLabels.getString("SaveGameMenu1"), e -> {
//            saveMyGame(window);
//        });
        
        return myFileMenuMaker;
    }
    
    private MenuBarElement getGameMenu(ITabPane window){
    	MenuBarElement myGameMenu = new MenuBarElement();
    	myGameMenu.setName(FrontEndData.ButtonLabels.getString("GameMenu"));
    	myGameMenu.setNewAction(FrontEndData.ButtonLabels.getString("PlayMenu1"), e -> {
             playMyGame(window);
        });
    	myGameMenu.setNewAction(FrontEndData.ButtonLabels.getString("AddNewLevelMenu1"), e -> {
            window.addNewTab();
        });
    	return myGameMenu;
    }
    
    private MenuBarElement getPlayToggle(MainAuthoringWindow mainAuthoringWindow){
    	MenuBarElement playToggleButton = new MenuBarElement();
    	playToggleButton.setName("play/edit");
    	playToggleButton.setNewAction("play", e -> {
    		MainPlayingWindow myMainPlayingWindow = new MainPlayingWindow(mainAuthoringWindow, myName);
    		mainAuthoringWindow.switchScene(myMainPlayingWindow);
    	});
    	return playToggleButton;
    }

	private void playMyGame(ITabPane tabLevels) {
		GameLoader.saveGame(myName, tabLevels);
		Stage myStage = (Stage) myMenuBar.getScene().getWindow();
		myStage.setScene(GameLoader.newGame(myName).getScene());
		myStage.centerOnScreen();
	}

	public MenuBar getMenuBar() {
		return myMenuBar;
	}

}