package authoringEnvironment.authoringToolbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;

import java.util.List;

import authoringEnvironment.LevelModel;
import authoringEnvironment.mainWindow.GameMakerWindow;
import gameplayer.GameLoader;

public class AuthoringMenubarCreator {
	private MenuBar myMenuBar;
	
	// TODO SWITCH TO REFLECTION
	private final String MENU_FILE = "File";
	private final String MENU_ITEM_NEW_FILE="New File";
	    
	private final String MENU_ADD = "Add";
	private final String MENU_ITEM_NEW_LEVEL = "Add New Level";
	
	private final String MENU_SAVE = "Save Game";
    private final String MENU_ITEM_SAVE_GAME = "Save Current Game";
    
	public AuthoringMenubarCreator(){
		myMenuBar = new MenuBar();
	}

	public void initMenuBar(GameMakerWindow window){
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
        	saveMyGame(window.getMyTabPane());
        });
        
        myMenuBar.getMenus().addAll(myFileMenuMaker.getMenu(), myNewLevelMaker.getMenu(), mySaveGameMenu.getMenu());
	}
	
	 private void saveMyGame(TabPane tabLevels) {
	    List<LevelModel> levelModels = GameLoader.levelTabsToModels(tabLevels);
	    GameLoader.saveGame(levelModels);
	 }

	public MenuBar getMenuBar(){
		return myMenuBar;
	}

}
