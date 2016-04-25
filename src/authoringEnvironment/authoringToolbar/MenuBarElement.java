package authoringEnvironment.authoringToolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * @author Joe Jacob
 */
public class MenuBarElement {
	private Menu myMenu;
	
	public MenuBarElement() {
	}
	
	/** 
	 * This makes a new item in the menu.
	 * @param itemName is the item being made
	 * @param action is the action set for this item
	 */
	
	public void setName(String menuName){
		myMenu = new Menu(menuName);
		myMenu.getStyleClass().add("menu-title");
	}
	
	public void setNewAction(String itemName, EventHandler<ActionEvent> action) {
		MenuItem myNewFile = new MenuItem(itemName);
        myNewFile.setOnAction(action);
        myMenu.getItems().add(myNewFile);
	}

	public Menu getMenu() {
		return myMenu;
	}


}
