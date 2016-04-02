package itemWindow;

import java.awt.Toolkit;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class ItemWindow {
	private TabPane myTabPane;
	private static final int myScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int myScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	private VBox myImagesBox;
	
	public ItemWindow(){
		myTabPane = new TabPane();
		myImagesBox = new VBox();
		myImagesBox.setPrefHeight(myScreenHeight);
		myImagesBox.setPrefWidth(0.3*myScreenWidth);
		Tab tab = new Tab();
		tab.setText("First Tab");
		tab.setContent(myImagesBox);
		myTabPane.getTabs().add(tab);
	}
	
	public TabPane getTabPane(){
		return myTabPane;
	}

}
