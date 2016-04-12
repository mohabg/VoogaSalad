package authoringEnvironment.authoringToolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public abstract class AbstractMenu implements IMenu {
	private Menu myMenu;
	
	public AbstractMenu(String menuName) {
		myMenu = new Menu(menuName);
	}
	
	@Override
	public void setNewAction(String itemName, EventHandler<ActionEvent> action) {
		MenuItem myNewFile = new MenuItem(itemName);
        myNewFile.setOnAction(action);
        myMenu.getItems().add(myNewFile);
	}

	@Override
	public Menu getMenu() {
		return myMenu;
	}


}
