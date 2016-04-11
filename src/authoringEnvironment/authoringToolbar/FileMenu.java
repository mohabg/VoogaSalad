package authoringEnvironment.authoringToolbar;
/**
 * @author davidyan
 */
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileMenu {
    private Menu myFileMenu;
    private MenuItem myNewFile;
    
    public FileMenu(){
    	myFileMenu = new Menu("File");
    	setNewAction();
    	myFileMenu.getItems().add(myNewFile);
    }
    
    public void setNewAction(){
    	myNewFile = new MenuItem("New");
        myNewFile.setOnAction(e-> System.out.println("HI"));

    }
    
    public Menu getMenu(){
    	return myFileMenu;
    }


}
