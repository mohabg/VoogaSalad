package authoringEnvironment.itemWindow;

import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import javafx.scene.control.TabPane;
import authoringEnvironment.mainWindow.GameMakerWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.*;
public class ItemWindow {
    private TabPane myTabPane;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private ArrayList<ViewSprite> playerSprites;
    private ArrayList<ViewSprite> enemySprites;
    private ArrayList<ViewSprite> backgroundImages;
    
    private PlayerSpritesTab playerSpritesTab;
    private EnemySpritesTab enemySpritesTab;
    private BackgroundImagesTab backgroundImagesTab;

    private Map<ViewSprite, Model> mySpritesAndModels;

    public ItemWindow(){
        myTabPane = new TabPane();
        mySpritesAndModels = new HashMap<ViewSprite, Model>();
        myTabPane.setPrefHeight(SCREEN_HEIGHT);
        myTabPane.setPrefWidth(0.25*SCREEN_WIDTH);
        
//        fillPlayerSprites();
//        fillEnemySprites();
//        fillBackgroundImages();
//
//        playerSpritesTab = new PlayerSpritesTab();
//        playerSpritesTab.populateTab(playerSprites);
//        playerSpritesTab.setTabTitle("Player");
//
//        enemySpritesTab = new EnemySpritesTab();
//        enemySpritesTab.populateTab(enemySprites);
//        enemySpritesTab.setTabTitle("Enemies");
//
//        backgroundImagesTab = new BackgroundImagesTab();
//        backgroundImagesTab.populateTab(backgroundImages);
//        backgroundImagesTab.setTabTitle("Backgrounds");
//
//        myTabPane.getTabs().addAll(playerSpritesTab.getTab(), enemySpritesTab.getTab(), backgroundImagesTab.getTab());
    }
    
    public void init(GameMakerWindow window){
        fillPlayerSprites();
        fillEnemySprites();
        fillBackgroundImages();

        playerSpritesTab = new PlayerSpritesTab();
        playerSpritesTab.populateTab(playerSprites);
        playerSpritesTab.setTabTitle("Player");

        enemySpritesTab = new EnemySpritesTab();
        enemySpritesTab.populateTab(enemySprites);
        enemySpritesTab.setTabTitle("Enemies");

        backgroundImagesTab = new BackgroundImagesTab();
        backgroundImagesTab.populateTab(backgroundImages);
        backgroundImagesTab.setTabTitle("Backgrounds");

        myTabPane.getTabs().addAll(playerSpritesTab.getTab(), enemySpritesTab.getTab(), backgroundImagesTab.getTab());

        for(ViewSprite viewSprite : playerSprites){
            // send viewsprite and the model by retrieving model from map
            // gamemaker will now make clone of both
            // game maker sends the model to the settings panel
            //
            viewSprite.setOnMouseClicked(e -> {window.addToWindow(viewSprite, mySpritesAndModels.get(viewSprite));});
        }
        
        for(ViewSprite viewSprite : enemySprites){
            viewSprite.setOnMouseClicked(e -> {window.addToWindow(viewSprite, mySpritesAndModels.get(viewSprite));});
        }
        
        for(ViewSprite viewSprite : backgroundImages){
            viewSprite.setOnMouseClicked(e -> {window.addToWindow(viewSprite, mySpritesAndModels.get(viewSprite));});
        }
    }
    
    private void fillPlayerSprites(){
        //use property file
        playerSprites = new ArrayList<ViewSprite>();
        ViewSprite galagaShip = new ViewSprite();
        galagaShip.setImage("pictures/galaga_ship.png");

        Model newModel = new Model();
        mySpritesAndModels.put(galagaShip, newModel);

        playerSprites.add(galagaShip);
    }
    
    private void fillEnemySprites(){
        //use property file
        enemySprites = new ArrayList<>();
        ViewSprite galagaEnemy1 = new ViewSprite();
        galagaEnemy1.setImage("pictures/galaga_enemy_1.png");
        Model newModel = new Model();
        mySpritesAndModels.put(galagaEnemy1, newModel);

        ViewSprite galagaEnemy2 = new ViewSprite();
        galagaEnemy2.setImage("pictures/galaga_enemy_2.png");

        Model neModel = new Model();
        mySpritesAndModels.put(galagaEnemy2, neModel);


        ViewSprite galagaEnemy3 = new ViewSprite();
        galagaEnemy3.setImage("pictures/galaga_enemy_3.png");

        Model nModel = new Model();
        mySpritesAndModels.put(galagaEnemy3, nModel);

        enemySprites.add(galagaEnemy1);
        enemySprites.add(galagaEnemy2);
        enemySprites.add(galagaEnemy3);
    }
    
    private void fillBackgroundImages(){
        //use property file
        backgroundImages = new ArrayList<ViewSprite>();
        ViewSprite background1 = new ViewSprite();
        background1.setImage("pictures/sky_background.png");
        ViewSprite background2 = new ViewSprite();
        background2.setImage("pictures/space_background.png");
        backgroundImages.add(background1);
        backgroundImages.add(background2);
    }
    
    public TabPane getTabPane(){
        return myTabPane;
    }
}