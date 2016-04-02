package itemWindow;

import java.awt.Toolkit;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class ItemWindow {
	private TabPane myTabPane;
	private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public ItemWindow(){
		myTabPane = new TabPane();
		myTabPane.setPrefHeight(SCREEN_HEIGHT);
		myTabPane.setPrefWidth(0.25*SCREEN_WIDTH);
		myTabPane.getTabs().addAll(createPlayerSpritesTab(), createEnemySpritesTab());
	}
	
	private Tab createPlayerSpritesTab(){
		Tab playerSprites = new Tab();
		playerSprites.setText("Player");
		
		TilePane playerSpritesTilePane = new TilePane();
		playerSpritesTilePane.setPrefTileHeight(myTabPane.getPrefWidth()*0.45);
		playerSpritesTilePane.setPrefTileWidth(myTabPane.getPrefWidth()*0.45);
		double inset = myTabPane.getPrefWidth()*0.033;
		playerSpritesTilePane.setPadding(new Insets(inset, inset, inset, inset));
		playerSpritesTilePane.setHgap(inset);
		
		ImageView img = new ImageView("pictures/galaga_ship.png");
		ImageView img2 = new ImageView("pictures/galaga_ship.png");
		ImageView img3 = new ImageView("pictures/galaga_ship.png");
		playerSpritesTilePane.getChildren().add(img);
		playerSpritesTilePane.getChildren().add(img2);
		playerSpritesTilePane.getChildren().add(img3);
		
		img.setOnMouseClicked(e -> {System.out.println("clicked 1");});
		img2.setOnMouseClicked(e -> {System.out.println("clicked 2");});
		img3.setOnMouseClicked(e -> {System.out.println("clicked 3");});
		
		playerSprites.setContent(playerSpritesTilePane);
		return playerSprites;
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
