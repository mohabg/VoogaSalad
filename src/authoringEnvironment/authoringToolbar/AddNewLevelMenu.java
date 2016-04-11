package authoringEnvironment.authoringToolbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import authoringEnvironment.mainWindow.GameMakerWindow;

/**
 * Created by davidyan on 4/6/16.
 */
public class AddNewLevelMenu {
    private Menu myLevelMenu;
    
    private final String ADD = "Add";
    private final String NEW_TAB = "Add New Tab";
    
    public AddNewLevelMenu(){
        myLevelMenu = new Menu(ADD);
    }

    public void setNewAction(GameMakerWindow myWindow){
    	MenuItem myNewMenuItem = new MenuItem(NEW_TAB);
        myNewMenuItem.setOnAction(e->myWindow.addNewTab());
        myLevelMenu.getItems().add(myNewMenuItem);
    }
    
    public Menu getMenu(){
        return myLevelMenu;
    }

}
