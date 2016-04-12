package authoringEnvironment.authoringToolbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.util.List;

import authoringEnvironment.LevelModel;
import authoringEnvironment.StartOptionsWindow;
import authoringEnvironment.mainWindow.GameMakerWindow;
import gameplayer.GameLoader;
import gameplayer.GamePlayingFileScreen;
import gameplayer.IScreen;
import interfaces.ITabPane;

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

	public AuthoringMenubarCreator() {
		myMenuBar = new MenuBar();
	}

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

		myMenuBar.getMenus().addAll(myFileMenuMaker.getMenu(), myNewLevelMaker.getMenu(), mySaveGameMenu.getMenu(),
				myBackMenu.getMenu());
	}

	private void setStartStage() {
		Stage myStage = (Stage) myMenuBar.getScene().getWindow();
        myStage.setScene(new StartOptionsWindow(myStage).getScene());
        myStage.centerOnScreen();
;

	}

	private void saveMyGame(ITabPane tabLevels) {
		List<LevelModel> levelModels = GameLoader.levelTabsToModels(tabLevels);
		GameLoader.saveGame(levelModels);
	}

	public MenuBar getMenuBar() {
		return myMenuBar;
	}

}
