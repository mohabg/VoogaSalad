package authoringEnvironment.authoringToolbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import authoringEnvironment.mainWindow.GameMakerWindow;

public class AuthoringMenubarCreator {
	private MenuBar myMenuBar;
	
	public AuthoringMenubarCreator(){
		myMenuBar = new MenuBar();
//		makeFileMenu();
	}

	public void init(GameMakerWindow window){
		FileMenu myFileMenuMaker = new FileMenu();
		myFileMenuMaker.setNewAction();
		
		AddNewLevelMenu myNewLevelMaker = new AddNewLevelMenu();
		myNewLevelMaker.setNewAction(window);

        SaveGameMenu mySaveGameMenu = new SaveGameMenu();
        mySaveGameMenu.addLevelTab(window);
        
        myMenuBar.getMenus().addAll(myFileMenuMaker.getMenu(), myNewLevelMaker.getMenu(), mySaveGameMenu.getMenu());
	}
	
//	private void makeFileMenu(){
////		FileMenu myFileMenuMaker = new FileMenu();
////		Menu myFileMenu = myFileMenuMaker.getMenu();
////		myMenuBar.getMenus().add(myFileMenu);
//	}


	public MenuBar getMenuBar(){
		return myMenuBar;
	}

}
