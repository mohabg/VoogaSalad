package authoringToolbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class AuthoringMenubarCreator {
	private MenuBar myMenuBar;
	
	public AuthoringMenubarCreator(){
		myMenuBar = new MenuBar();
		makeFileMenu();
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
