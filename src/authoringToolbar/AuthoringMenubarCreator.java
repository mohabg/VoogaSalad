package authoringToolbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import mainWindow.GameMakerWindow;

public class AuthoringMenubarCreator {
	private MenuBar myMenuBar;
	
	public AuthoringMenubarCreator(){
		myMenuBar = new MenuBar();
//		makeFileMenu();
	}

	public void init(GameMakerWindow window){
		FileMenu myFileMenuMaker = new FileMenu();
		Menu myFileMenu = myFileMenuMaker.getMenu();
		myMenuBar.getMenus().add(myFileMenu);

		AddNewLevelMenu myNewLevelMaker = new AddNewLevelMenu();
		myNewLevelMaker.setNewAction(window);
		Menu myAddNewTab = myNewLevelMaker.getMenu();
		myMenuBar.getMenus().add(myAddNewTab);

	}
	
	private void makeFileMenu(){
//		FileMenu myFileMenuMaker = new FileMenu();
//		Menu myFileMenu = myFileMenuMaker.getMenu();
//		myMenuBar.getMenus().add(myFileMenu);
	}


	public MenuBar getMenuBar(){
		return myMenuBar;
	}

}
