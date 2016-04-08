package authoringEnvironment;

import java.awt.Toolkit;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class Settings {
	private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	private static final int mySpacing = 20;

	public Settings() {

	}

	public void setTilePaneSettings(TilePane tilepane) {
		tilepane.setPrefTileHeight(200);
		tilepane.setPrefTileWidth(200);
		double inset = 5;
		tilepane.setPadding(new Insets(inset, inset, inset, inset));
		tilepane.setHgap(inset);
	}

	public void setTabPaneSettings(TabPane myTabPane) {
		myTabPane.setPrefHeight(SCREEN_HEIGHT);
		myTabPane.setPrefWidth(0.25 * SCREEN_WIDTH);
	}

	public void setGameAreaSettings(ScrollPane myNewGameArea) {
		myNewGameArea.setFitToWidth(true);
		myNewGameArea.setPrefHeight(SCREEN_HEIGHT);
		myNewGameArea.setPrefWidth(0.4 * SCREEN_WIDTH);
	}

	public void setGamePaneSettings(AnchorPane myNewGamePane) {
		myNewGamePane.getStyleClass().add("pane");

		myNewGamePane.setPrefWidth(0.3 * SCREEN_WIDTH);
		myNewGamePane.setPrefHeight(SCREEN_HEIGHT);
	}

	public void setSliderSettings(Slider mySlider) {
		mySlider.setShowTickMarks(true);
		mySlider.setShowTickLabels(true);
	}

	public void setSettingsDisplaySettings(VBox myDisplay){
		myDisplay.setPrefWidth(SCREEN_WIDTH*0.3);
		myDisplay.setPrefHeight(SCREEN_HEIGHT);
	}

	public void setStartWindowSettings(VBox startWindowBox){
	startWindowBox.setPadding(new Insets(mySpacing, mySpacing, mySpacing, mySpacing));
	startWindowBox.setSpacing(mySpacing);
	startWindowBox.setAlignment(Pos.CENTER);
	}
}
