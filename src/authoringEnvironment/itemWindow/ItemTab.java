// This entire file is part of my masterpiece.
// Sam Toffler

// The idea behind my masterpiece is to improve the flexibility of our item window.
// Prior to my changes, the item window was very specific to our project, mainly because
// its worked revolved around ViewSprites (a class that we created). I think I have
// improved up the design of the item window enough to make it a utility. In the end it
// might require a little more work form the programmer but it is more extensible and is
// now pretty closed. The item window is now merely a tool to display all of the sprites
// exist in a project. I've limited responsibility by ensuring that ItemTab.java is a
// class that holds a TilePane that holds strictly images. The programmer can also specify
// actions to be linked with those images. For example, the programmer might want to
// create a pop-up box that the user can use to change that sprite's qualities, or have
// the sprites be added directly to the main authoring window as was the case with our
// project. However, it should be noted that it is the job of the programmer to create
// these functionalities and they do not exist in ItemTab.java.

package authoringEnvironment.itemWindow;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public  class ItemTab{
	
	private Tab myTab;
    private TilePane myTilePane;

    public ItemTab() {
        myTab = new Tab();
        myTilePane = new TilePane();
    	createCustomImageButton();
    }
    
    private void createCustomImageButton(){
    	FileChooser fileChooser = new FileChooser();
    	Button addButton = new Button("+");
    	myTilePane.getChildren().add(addButton);
    	addButton.setOnAction(e -> {File imageToAdd = fileChooser.showOpenDialog(new Stage());
    		addImageToTab(imageToAdd.getAbsolutePath());});
    }
    
    public void addImageToTab(String filePath){
    	Image i = new Image(filePath);
    	ImageView iv = new ImageView(i);
    	myTilePane.getChildren().add(iv);
    	myTab.setContent(myTilePane);
    }
    
    public void addFunctionality(ImageView iv, EventHandler<MouseEvent> e){
    	int tileToEdit = myTilePane.getChildren().indexOf(iv);
    	myTilePane.getChildren().get(tileToEdit).setOnMouseClicked(e);
    }
    
    public void setTabTitle(String title){
    	myTab.setText(title);
    }
    
    public Tab getTab(){
    	return myTab;
    }
}