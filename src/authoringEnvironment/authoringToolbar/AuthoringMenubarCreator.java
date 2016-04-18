package authoringEnvironment.authoringToolbar;

import authoringEnvironment.LevelModel;
import authoringEnvironment.MainAuthoringWindow;
import authoringEnvironment.StartOptionsWindow;
import gameplayer.GameLoader;
import gameplayer.GamePlayingFileScreen;
import interfaces.ITabPane;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.FlowPane;
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

<<<<<<< HEAD
	private final String MENU_BACK = "Back";
	private final String MENU_ITEM_BACK = "Back";
	
	private final String MENU_PLAY = "Play";
	private final String MENU_ITEM_PLAY = "Play";
	
	private final String MENU_GAME = "Game";
	private final String MENU_ITEM_GAME = "Game Settings";
=======
>>>>>>> a3dbc981ce7722156b2fcbe7d1c68dd621256d6e

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
<<<<<<< HEAD
		
		GameMenu myGameMenu = new GameMenu(MENU_GAME);
		myGameMenu.setNewAction(MENU_ITEM_GAME, e-> {
			// needs some obvious refactoring
			FlowPane gameSettingsPane = new FlowPane();
			Scene gameSettingsScene = new Scene(gameSettingsPane, 200, 200);
			Stage gameSettingsStage = new Stage();
			gameSettingsStage.setScene(gameSettingsScene);
			gameSettingsStage.show();
		});
		
        myMenuBar.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
=======
		myMenuBar.getStylesheets().add(FrontEndData.STYLESHEET);
>>>>>>> a3dbc981ce7722156b2fcbe7d1c68dd621256d6e
		myMenuBar.getMenus().addAll(myFileMenuMaker.getMenu(), myNewLevelMaker.getMenu(), mySaveGameMenu.getMenu(),
				myBackMenu.getMenu(), myPlayMenu.getMenu(), myGameMenu.getMenu());
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
