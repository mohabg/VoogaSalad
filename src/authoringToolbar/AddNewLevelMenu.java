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

    public AddNewLevelMenu(){
        myFileMenu = new Menu("Add");
    }

    public void setNewAction(GameMakerWindow myWindow){
        myNewFile = new MenuItem("Add New Tab");
        myNewFile.setOnAction(e->myWindow.addNewTab());
        myFileMenu.getItems().add(myNewFile);


    }

    public Menu getMenu(){
        return myFileMenu;
    }

}
