package itemWindow;

import java.awt.Toolkit;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import mainWindow.GameMakerWindow;

public class ItemWindow {
	private TabPane myTabPane;
	private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	private GameMakerWindow myGameMakerWindow;
	private TilePane playerSpritesTilePane;

	public ItemWindow(GameMakerWindow testy){
		myGameMakerWindow = testy;
		myTabPane = new TabPane();
		myTabPane.setPrefHeight(SCREEN_HEIGHT);
		myTabPane.setPrefWidth(0.25*SCREEN_WIDTH);
		createPlayerSpritesTab();

	}
	
	private void createPlayerSpritesTab(){
		Tab playerSprites = new Tab();
		playerSprites.setText("Player");
		
		playerSpritesTilePane = new TilePane();
		playerSpritesTilePane.setPrefTileHeight(myTabPane.getPrefWidth()*0.45);
		playerSpritesTilePane.setPrefTileWidth(myTabPane.getPrefWidth()*0.45);
		double inset = myTabPane.getPrefWidth()*0.033;
		playerSpritesTilePane.setPadding(new Insets(inset, inset, inset, inset));
		playerSpritesTilePane.setHgap(inset);
		
		ImageView img = new ImageView("pictures/galaga_ship.png");
		ImageView img2 = new ImageView("pictures/galaga_enemy_1.png");
		ImageView img3 = new ImageView("pictures/player3.gif");
		ImageView img4 = new ImageView("pictures/helicopter.gif");
		ImageView img5 = new ImageView("pictures/cipher.png");
		ImageView img6 = new ImageView("pictures/Frogfoot.png");

		playerSpritesTilePane.getChildren().add(img);
		playerSpritesTilePane.getChildren().add(img2);
		playerSpritesTilePane.getChildren().add(img3);
		playerSpritesTilePane.getChildren().add(img4);
		playerSpritesTilePane.getChildren().add(img5);
		playerSpritesTilePane.getChildren().add(img6);

		playerSprites.setContent(playerSpritesTilePane);
		img.setOnMouseClicked(e -> {
			myGameMakerWindow.addImageToWindow("pictures/galaga_ship.png");
			});
		img2.setOnMouseClicked(e -> {myGameMakerWindow.addImageToWindow("pictures/galaga_enemy_1.png");});
		img3.setOnMouseClicked(e -> {myGameMakerWindow.addImageToWindow("pictures/player3.gif");});
		img4.setOnMouseClicked(e -> {
			myGameMakerWindow.addImageToWindow("pictures/helicopter.gif");
			});
		img5.setOnMouseClicked(e -> {myGameMakerWindow.addImageToWindow("pictures/cipher.png");});
		img6.setOnMouseClicked(e -> {myGameMakerWindow.addImageToWindow("pictures/Frogfoot.png");});

		myTabPane.getTabs().add(playerSprites);
		
		
	}
	

	private Tab createEnemySpritesTab(){
		Tab enemySprites = new Tab();
		enemySprites.setText("Enemies");
		return enemySprites;
	}
	
	public TabPane getTabPane(){
		return myTabPane;
	}

}
