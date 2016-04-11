package authoringEnvironment.authoringToolbar;
/**
 * @author davidyan
 */
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileMenu {
    private Menu myFileMenu;
    
    private final String FILE = "File";
    private final String NEW_FILE="New File";
    
    public FileMenu(){
    	myFileMenu = new Menu(FILE);
    	setNewAction();
    }
    
    public void setNewAction(){
    	MenuItem myNewFile = new MenuItem(NEW_FILE);
    	// TODO replace this with an actual function
        myNewFile.setOnAction(e-> System.out.println("HI"));
        myFileMenu.getItems().add(myNewFile);
    }
    
    public Menu getMenu(){
    	return myFileMenu;
    }


}
