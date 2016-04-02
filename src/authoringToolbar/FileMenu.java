package authoringToolbar;
/**
 * @author davidyan
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        myNewFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
            	System.out.println("New");
            }
        });

    }
    
    public Menu getMenu(){
    	return myFileMenu;
    }


}
