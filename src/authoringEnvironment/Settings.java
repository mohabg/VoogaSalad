package authoringEnvironment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.awt.*;

/**
 * @author Huijia Yu
 */
public class Settings {
	private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final double ITEM_WINDOW_WIDTH = SCREEN_WIDTH * 0.25;
    private static final int mySpacing = 20;




	// TODO: REPLACE THE CONSTANTS IN HERE
	public Settings() {

	}

	public static void setTilePaneSettings(TilePane tilepane) {
		tilepane.setPrefTileHeight(ITEM_WINDOW_WIDTH / 3);
		tilepane.setPrefTileWidth(ITEM_WINDOW_WIDTH / 3);
		// double inset = 5;
		// tilepane.setPadding(new Insets(inset, inset, inset, inset));
		// tilepane.setHgap(inset);
	}

	public static void setTabPaneSettings(TabPane myTabPane) {
		myTabPane.setPrefHeight(SCREEN_HEIGHT);
		myTabPane.setPrefWidth(0.25 * SCREEN_WIDTH);
	}
	
	public static void setControlWindowSettings(Pane myPane){
		myPane.setPrefHeight(SCREEN_HEIGHT);
		myPane.setPrefWidth(0.25 * SCREEN_WIDTH);
	}
	
	public static void setPlayScreenSettings(Pane myPane){
		myPane.setPrefWidth(0.45 * SCREEN_WIDTH);
		myPane.setPrefHeight(SCREEN_HEIGHT);
	}
	
	public static void setHUDWindowSettings(Pane myPane){
		myPane.setPrefWidth(SCREEN_WIDTH * 0.3);
		myPane.setPrefHeight(SCREEN_HEIGHT);
	}

	public static void setGamePaneSettings(AnchorPane myNewGamePane) {
		myNewGamePane.getStyleClass().add("pane");
		myNewGamePane.setPrefWidth(0.45 * SCREEN_WIDTH);
		myNewGamePane.setPrefHeight(SCREEN_HEIGHT);
	}

	public static void setSliderSettings(Slider mySlider) {
		mySlider.setShowTickMarks(true);
		mySlider.setShowTickLabels(true);
	}

	public static void setSettingsDisplaySettings(VBox myDisplay) {
		myDisplay.setPrefWidth(SCREEN_WIDTH * 0.3);
		myDisplay.setPrefHeight(SCREEN_HEIGHT);
	}

	public static void setStartWindowSettings(VBox startWindowBox) {
		startWindowBox.setPadding(new Insets(mySpacing, mySpacing, mySpacing, mySpacing));
		startWindowBox.setSpacing(mySpacing);
		startWindowBox.setAlignment(Pos.CENTER);
	}

	public static void setGamePlayingSettings(Pane pane) {
		pane.setPrefHeight(SCREEN_HEIGHT);
		pane.setPrefWidth(0.4 * SCREEN_WIDTH);

	}

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

}
