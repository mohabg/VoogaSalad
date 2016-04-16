package authoringEnvironment.authoringToolbar;

import authoringEnvironment.LevelModel;
import authoringEnvironment.StartOptionsWindow;
import gameplayer.GameLoader;
import gameplayer.GamePlayingFileScreen;
import interfaces.ITabPane;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.List;
/** 
 * This is the creator for the menubar, which allows the user to select new files,
 *  add a level, save, or return to the start screen
 * @author Huijia, David Yan, Joe Jacob
 *
 */
public class AuthoringMenubarCreator {
	private MenuBar myMenuBar;

	// TODO SWITCH TO REFLECTION
	private final String MENU_FILE = "File";
	private final String MENU_ITEM_NEW_FILE = "New File";

	private final String MENU_ADD = "Add";
	private final String MENU_ITEM_NEW_LEVEL = "Add New Level";

	private final String MENU_SAVE = "Save Game";
	private final String MENU_ITEM_SAVE_GAME = "Save Current Game";

	private final String MENU_BACK = "Back";
	private final String MENU_ITEM_BACK = "Back";
	
	private final String MENU_PLAY = "Play";
	private final String MENU_ITEM_PLAY = "Play";
	
	private final String MENU_GAME = "Game";
	private final String MENU_ITEM_GAME = "Game Settings";

	public AuthoringMenubarCreator() {
		myMenuBar = new MenuBar();
	}
	/** 
	 * This method initializes the menubar using the tabpane (to set the add tab action). 
	 * We made this to eliminate dependencies.
	 * 
	 * @param window
	 */
	public void initMenuBar(ITabPane window) {
		FileMenu myFileMenuMaker = new FileMenu(MENU_FILE);
		myFileMenuMaker.setNewAction(MENU_ITEM_NEW_FILE, e -> {
			System.out.println("NOT IMPLEMENTED");
		});

		AddNewLevelMenu myNewLevelMaker = new AddNewLevelMenu(MENU_ADD);
		myNewLevelMaker.setNewAction(MENU_ITEM_NEW_LEVEL, e -> {
			window.addNewTab();
		});

		SaveGameMenu mySaveGameMenu = new SaveGameMenu(MENU_SAVE);
		mySaveGameMenu.setNewAction(MENU_ITEM_SAVE_GAME, e -> {
			saveMyGame(window);
		});

		BackMenu myBackMenu = new BackMenu(MENU_BACK);
		myBackMenu.setNewAction(MENU_ITEM_BACK, e -> {
			setStartStage();
		});
		
		PlayMenu myPlayMenu	= new PlayMenu(MENU_PLAY);
		myPlayMenu.setNewAction(MENU_ITEM_PLAY, e-> {
			playMyGame(window);
		});
		
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
		myMenuBar.getMenus().addAll(myFileMenuMaker.getMenu(), myNewLevelMaker.getMenu(), mySaveGameMenu.getMenu(),
				myBackMenu.getMenu(), myPlayMenu.getMenu(), myGameMenu.getMenu());
	}

	private void setStartStage() {
		Stage myStage = (Stage) myMenuBar.getScene().getWindow();
        myStage.setScene(new StartOptionsWindow(myStage).getScene());
        myStage.centerOnScreen();
	}

	private void saveMyGame(ITabPane tabLevels) {
		List<LevelModel> levelModels = GameLoader.levelTabsToModels(tabLevels);
		GameLoader.saveGame(levelModels);
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
