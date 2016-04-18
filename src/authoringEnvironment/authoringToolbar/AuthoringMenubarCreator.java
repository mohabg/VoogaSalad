package authoringEnvironment.authoringToolbar;

import authoringEnvironment.LevelModel;
import authoringEnvironment.MainAuthoringWindow;
import authoringEnvironment.StartOptionsWindow;
import gameplayer.GameLoader;
import gameplayer.GamePlayingFileScreen;
import interfaces.ITabPane;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import resources.FrontEndData;

import java.util.List;

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
				
		
		FileMenu myFileMenuMaker = new FileMenu();
//		myFileMenuMaker.setName(FrontEndData.ButtonLabels.getString(myFileMenuMaker.getClass().getName();
		myFileMenuMaker.setName(FrontEndData.ButtonLabels.getString("FileMenu"));
		myFileMenuMaker.setNewAction(FrontEndData.ButtonLabels.getString("FileMenu1"), e -> {
			System.out.println("NOT IMPLEMENTED");
		});

		AddNewLevelMenu myNewLevelMaker = new AddNewLevelMenu();
		myNewLevelMaker.setName(FrontEndData.ButtonLabels.getString("AddNewLevelMenu"));

		myNewLevelMaker.setNewAction(FrontEndData.ButtonLabels.getString("AddNewLevelMenu1"), e -> {
			window.addNewTab();
		});

		SaveGameMenu mySaveGameMenu = new SaveGameMenu();
		mySaveGameMenu.setName(FrontEndData.ButtonLabels.getString("SaveGameMenu"));

		mySaveGameMenu.setNewAction(FrontEndData.ButtonLabels.getString("SaveGameMenu1"), e -> {
			saveMyGame(window);
		});

		BackMenu myBackMenu = new BackMenu();
		myBackMenu.setName(FrontEndData.ButtonLabels.getString("BackMenu"));

		myBackMenu.setNewAction(FrontEndData.ButtonLabels.getString("BackMenu1"), e -> {
			mainAuthoringWindow.returnToParentScreen();

		});

		PlayMenu myPlayMenu = new PlayMenu();
		myPlayMenu.setName(FrontEndData.ButtonLabels.getString("PlayMenu"));

		myPlayMenu.setNewAction(FrontEndData.ButtonLabels.getString("PlayMenu1"), e -> {
			playMyGame(window);
		});
		myMenuBar.getStylesheets().add(FrontEndData.STYLESHEET);
		myMenuBar.getMenus().addAll(myFileMenuMaker.getMenu(), myNewLevelMaker.getMenu(), mySaveGameMenu.getMenu(),
				myBackMenu.getMenu(), myPlayMenu.getMenu());
	}

	private void saveMyGame(ITabPane tabLevels) {
		GameLoader.saveGame(tabLevels);
	}

	private void playMyGame(ITabPane tabLevels) {
		saveMyGame(tabLevels);
		Stage myStage = (Stage) myMenuBar.getScene().getWindow();
		myStage.setScene(new GamePlayingFileScreen().getScene());
		myStage.centerOnScreen();
	}

	public MenuBar getMenuBar() {
		return myMenuBar;
	}

}
