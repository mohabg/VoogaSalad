package itemWindow;

import java.awt.Toolkit;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import mainWindow.GameMakerWindow;

public class ItemWindow {
	private TabPane myTabPane;
	private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	private GameMakerWindow myGameMakerWindow;
	private ArrayList<String> playerSprites;
	private ArrayList<String> enemySprites;
	private ArrayList<String> backgroundImages;
	
	
	public ItemWindow(GameMakerWindow window){
		myGameMakerWindow = window;
		myTabPane = new TabPane();
		myTabPane.setPrefHeight(SCREEN_HEIGHT);
		myTabPane.setPrefWidth(0.25*SCREEN_WIDTH);
		fillPlayerSprites();
		fillEnemySprites();
		fillBackgroundImages();
		createSpriteTab("Player", playerSprites, true);
		createSpriteTab("Enemies", enemySprites, true);
		createSpriteTab("Backgrounds", backgroundImages, false);
	}
	
	private void fillPlayerSprites(){
		//use property file
		playerSprites = new ArrayList<String>();
		playerSprites.add("pictures/galaga_ship.png");
	}
	
	private void fillEnemySprites(){
		//use property file
		enemySprites = new ArrayList<String>();
		enemySprites.add("pictures/galaga_enemy_1.png");
		enemySprites.add("pictures/galaga_enemy_2.png");
		enemySprites.add("pictures/galaga_enemy_3.png");
	}
	
	private void fillBackgroundImages(){
		//use property file
		backgroundImages = new ArrayList<String>();
		backgroundImages.add("pictures/sky_background.png");
		backgroundImages.add("pictures/space_background.png");
	}
	
	private void createSpriteTab(String tabTitle, ArrayList<String> spriteImages, boolean isSprite){
		Tab tab = new Tab();
		tab.setText(tabTitle);
		
		TilePane tilePane = new TilePane();
		setTilePaneSettings(tilePane);
		populateSpriteTilePane(tilePane, spriteImages, isSprite);
		
		tab.setContent(tilePane);
		myTabPane.getTabs().add(tab);
	}
	
	private void populateSpriteTilePane(TilePane tp, ArrayList<String> spriteImages, boolean isSprite){
		for(String x : spriteImages){
			ImageView img = new ImageView(x);
			img.setOnMouseClicked(e -> {myGameMakerWindow.addToWindow(img, isSprite);});
			img.setFitHeight(myTabPane.getPrefWidth()*0.25);
			img.setFitWidth(myTabPane.getPrefWidth()*0.25);
			tp.getChildren().add(img);
		}
	}
	
	private void setTilePaneSettings(TilePane tp){
		tp.setPrefTileHeight(myTabPane.getPrefWidth()*0.45);
		tp.setPrefTileWidth(myTabPane.getPrefWidth()*0.45);
		double inset = myTabPane.getPrefWidth()*0.033;
		tp.setPadding(new Insets(inset, inset, inset, inset));
		tp.setHgap(inset);
	}
	
	public TabPane getTabPane(){
		return myTabPane;
	}
}
