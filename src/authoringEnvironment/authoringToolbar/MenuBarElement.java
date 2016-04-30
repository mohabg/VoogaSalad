package authoringEnvironment.authoringToolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import resources.FrontEndData;

/**
 * @author Joe Jacob
 */
public class MenuBarElement {
	private Menu myMenu;
	
	public MenuBarElement() {
	}
	
	/** 
	 * This makes a new item in the menu.
	 * @param menuName is the item being made
	 */
	
	public void setName(String menuName){
		myMenu = new Menu(menuName);
		myMenu.getStyleClass().add(FrontEndData.StyleLabels.getString("MenuTitle"));
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
