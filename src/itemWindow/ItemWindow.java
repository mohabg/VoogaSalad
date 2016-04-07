package itemWindow;

import authoringEnvironment.ViewSprite;
import javafx.scene.control.TabPane;
import mainWindow.GameMakerWindow;

import java.awt.*;
import java.util.ArrayList;

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
    
    public ItemWindow(){
        myTabPane = new TabPane();
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
            viewSprite.getImageView().setOnMouseClicked(e -> {window.addToWindow(viewSprite);});
        }
        
        for(ViewSprite viewSprite : enemySprites){
            viewSprite.getImageView().setOnMouseClicked(e -> {window.addToWindow(viewSprite);});
        }
        
        for(ViewSprite viewSprite : backgroundImages){
            viewSprite.getImageView().setOnMouseClicked(e -> {window.addToWindow(viewSprite);});
        }
    }
    
    private void fillPlayerSprites(){
        //use property file
        playerSprites = new ArrayList<ViewSprite>();
        ViewSprite galagaShip = new ViewSprite();
        galagaShip.setImage("pictures/galaga_ship.png");
        playerSprites.add(galagaShip);
    }
    
    private void fillEnemySprites(){
        //use property file
        enemySprites = new ArrayList<>();
        ViewSprite galagaEnemy1 = new ViewSprite();
        galagaEnemy1.setImage("pictures/galaga_enemy_1.png");
        ViewSprite galagaEnemy2 = new ViewSprite();
        galagaEnemy2.setImage("pictures/galaga_enemy_2.png");
        ViewSprite galagaEnemy3 = new ViewSprite();
        galagaEnemy3.setImage("pictures/galaga_enemy_3.png");
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