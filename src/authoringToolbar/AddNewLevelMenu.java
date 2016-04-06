package authoringToolbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import mainWindow.GameMakerWindow;

/**
 * Created by davidyan on 4/6/16.
 */
public class AddNewLevelMenu {
    private Menu myFileMenu;
    private MenuItem myNewFile;
    private GameMakerWindow myWindow;

    public AddNewLevelMenu(GameMakerWindow window){
        myWindow = window;
        myFileMenu = new Menu("Add");
        setNewAction();
        myFileMenu.getItems().add(myNewFile);
    }

    public void setNewAction(){
        myNewFile = new MenuItem("Add New Tab");
        myNewFile.setOnAction(e->myWindow.addNewTab());

    }

    public Menu getMenu(){
        return myFileMenu;
    }

}
