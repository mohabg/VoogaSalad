package authoringToolbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import mainWindow.GameMakerWindow;

public class AuthoringMenubarCreator {
	private MenuBar myMenuBar;
	
	public AuthoringMenubarCreator(){
		myMenuBar = new MenuBar();
		makeFileMenu();
	}

	public void init(GameMakerWindow window){
		AddNewLevelMenu myNewLevelMaker = new AddNewLevelMenu(window);
		Menu myFileMenu = myNewLevelMaker.getMenu();
		myMenuBar.getMenus().add(myFileMenu);

	}
	
	private void makeFileMenu(){
		FileMenu myFileMenuMaker = new FileMenu();
		Menu myFileMenu = myFileMenuMaker.getMenu();
		myMenuBar.getMenus().add(myFileMenu);
	}


	public MenuBar getMenuBar(){
		return myMenuBar;
	}

}
