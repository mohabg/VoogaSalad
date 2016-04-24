package authoringEnvironment.authoringToolbar;

import authoringEnvironment.MainAuthoringWindow;
import gameplayer.GameLoader;
import gameplayer.GamePlayingFileScreen;
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

	// TODO SWITCH TO REFLECTION
	public AuthoringMenubarCreator() {
		myMenuBar = new MenuBar();
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
		BackMenu myBackMenu = getBackMenu(mainAuthoringWindow);
        FileMenu myFileMenuMaker = getFileMenu(window);
        GameMenu myGameMenu = getGameMenu(window);
		myMenuBar.getStylesheets().add("authoringEnvironment/authoringToolbar/authoringToolbar.css");
		myMenuBar.getMenus().addAll(myBackMenu.getMenu(), myFileMenuMaker.getMenu(), myGameMenu.getMenu());
	}

    private BackMenu getBackMenu(MainAuthoringWindow mainAuthoringWindow) {
        BackMenu myBackMenu = new BackMenu();
        myBackMenu.setName(FrontEndData.ButtonLabels.getString("BackMenu"));
        myBackMenu.setNewAction(FrontEndData.ButtonLabels.getString("BackMenu1"), e -> {
            mainAuthoringWindow.returnToParentScreen();
        });
        return myBackMenu;
    }

    private FileMenu getFileMenu(ITabPane window) {
        FileMenu myFileMenuMaker = new FileMenu();
        myFileMenuMaker.setName(FrontEndData.ButtonLabels.getString("FileMenu"));
        myFileMenuMaker.setNewAction(FrontEndData.ButtonLabels.getString("FileMenu1"), e -> {
            System.out.println("NOT IMPLEMENTED");
        });
        myFileMenuMaker.setNewAction(FrontEndData.ButtonLabels.getString("SaveGameMenu1"), e -> {
            saveMyGame(window);
        });
        
        return myFileMenuMaker;
    }
    
    private GameMenu getGameMenu(ITabPane window){
    	GameMenu myGameMenu = new GameMenu();
    	myGameMenu.setName(FrontEndData.ButtonLabels.getString("GameMenu"));
    	myGameMenu.setNewAction(FrontEndData.ButtonLabels.getString("PlayMenu1"), e -> {
             playMyGame(window);
        });
    	myGameMenu.setNewAction(FrontEndData.ButtonLabels.getString("AddNewLevelMenu1"), e -> {
            window.addNewTab();
        });
    	return myGameMenu;
    }

    private void saveMyGame(ITabPane tabLevels) {
		GameLoader.saveGame(tabLevels);
	}

	private void playMyGame(ITabPane tabLevels) {
		GameLoader.saveGame(tabLevels);
		Stage myStage = (Stage) myMenuBar.getScene().getWindow();
		myStage.setScene(new GamePlayingFileScreen().getScene());
		myStage.centerOnScreen();
	}

	public MenuBar getMenuBar() {
		return myMenuBar;
	}

}
