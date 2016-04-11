package authoringEnvironment.authoringToolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;

public interface IMenu {
	void setNewAction(String itemName, EventHandler<ActionEvent> action);
	
	Menu getMenu();
}
