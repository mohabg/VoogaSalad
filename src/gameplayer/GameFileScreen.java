package gameplayer;


import authoringEnvironment.Settings;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import resources.FrontEndData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Abstract class implementing IScreen, used to display available/saved games
 *
 * @author Huijia
 *
 */
public abstract class GameFileScreen extends Screen {
    private File myGameFile;
    private TabPane tabPane;
        
    public GameFileScreen() {
        super();
        tabPane = new TabPane();
        tabPane.getStylesheets().add("gameplayer/gameFileScreen.css");
        // Settings.setGamePlayingSettings((Pane) tabPane);
        initTabs();
    }
    
    private void initTabs() {
        tabPane.getTabs().add(addTab(FrontEndData.ButtonLabels.getString("defaultgames"), FrontEndData.DEFAULT_DIRECTORY));
        tabPane.getTabs().add(addTab(FrontEndData.ButtonLabels.getString("savedgames"), FrontEndData.SAVED_DIRECTORY));
        BorderPane pane = new BorderPane();
        pane.setTop(ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("backtostart"), a-> {returnToStart();}));
        myPane.getChildren().add(tabPane);
    }
    
    private Tab addTab(String title, File directory) {
        Tab tab = new Tab();
        tab.setText(title);
        ScrollPane myScroller = new ScrollPane();
        myScroller.setContent(makeFlowPane(directory));
        tab.setContent(myScroller);
        return tab;
    }
    
    private FlowPane makeFlowPane(File flowDirectory) {
        FlowPane flowPane = new FlowPane();
        flowPane.getChildren()
        .addAll(Arrays.stream(getGames(flowDirectory)).map(f -> makeDisplay(f)).collect(Collectors.toList()));
        flowPane.setPrefHeight(Settings.getScreenHeight());
        flowPane.setPrefWidth(0.4*Settings.getScreenWidth());
        return flowPane;
    }
    
    private File[] getGames(File directory) {
        return directory.listFiles(f -> f.getName().endsWith(FrontEndData.FILE_TYPE));
    }
    
    /**
     * Creates an Imageview for a file, setting its onclick action to load it in
     * the player or in the environment depending on the subclass.
     *
     * @param file
     * @return
     */
    
    public VBox makeDisplay(File file) {
//        ImageView imageview = new ImageView();
//        // TODO have this pull the saved game's picture
//        imageview.setImage(new Image(FrontEndData.DEFAULT_IMAGE));
//        imageview.setOnMouseClicked((event) -> {
//            setOnMouseClick(file);
//        });
//        Label label = new Label(file.getName().replace(".xml", ""));
        Button myButton = new Button(file.getName().replace(".xml",""));
        myButton.setOnMouseClicked((event) -> {
            setOnMouseClick(file);
        });
        myButton.setPrefHeight(FrontEndData.BUTTON_SIZE);
        myButton.setPrefWidth(FrontEndData.BUTTON_SIZE);
        
//      myButton.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
        myButton.getStylesheets().add("authoringEnvironment/itemWindow/styles.css");
        myButton.setId("button-style");
        return new VBox(myButton);
        
    }
    
    public abstract void setOnMouseClick(File file);
    
    public File getGameFile() {
        return myGameFile;
    }
    

}