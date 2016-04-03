package itemWindow;

import java.awt.Toolkit;

import authoringEnvironment.MainAuthoringWindow;
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
	private ImageView img;
	private TilePane playerSpritesTilePane;

	public ItemWindow(GameMakerWindow testy){
		myGameMakerWindow = testy;
		myTabPane = new TabPane();
		myTabPane.setPrefHeight(SCREEN_HEIGHT);
		myTabPane.setPrefWidth(0.25*SCREEN_WIDTH);
		createPlayerSpritesTab();
		//myTabPane.getTabs().addAll(createPlayerSpritesTab(), createEnemySpritesTab());

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
		
		img = new ImageView("pictures/galaga_ship.png");
		ImageView img2 = new ImageView("pictures/galaga_ship.png");
		ImageView img3 = new ImageView("pictures/galaga_ship.png");
		playerSpritesTilePane.getChildren().add(img);
		playerSpritesTilePane.getChildren().add(img2);
		playerSpritesTilePane.getChildren().add(img3);
		playerSprites.setContent(playerSpritesTilePane);
		img.setOnMouseClicked(e -> {myGameMakerWindow.addImageToWindow();});
//		img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event){
//				myGameMakerWindow.addImageToWindow();
//			}
//		});

		img2.setOnMouseClicked(e -> {System.out.println("clicked 2");});
		img3.setOnMouseClicked(e -> {System.out.println("clicked 3");});
		myTabPane.getTabs().add(playerSprites);
		
		
	}
	public void add(){
		myGameMakerWindow.addImageToWindow();
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
