package authoringEnvironment.authoringToolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * @author Joe Jacob
 */
public abstract class AbstractMenu {
	private Menu myMenu;
	
	public AbstractMenu(String menuName) {
		myMenu = new Menu(menuName);
	}
	
	/** 
	 * This makes a new item in the menu.
	 * @param itemName is the item being made
	 * @param action is the action set for this item
	 */
	
	public void setNewAction(String itemName, EventHandler<ActionEvent> action) {
		MenuItem myNewFile = new MenuItem(itemName);
        myNewFile.setOnAction(action);
        myMenu.getItems().add(myNewFile);
	}

	public Menu getMenu() {
		return myMenu;
	}


}
