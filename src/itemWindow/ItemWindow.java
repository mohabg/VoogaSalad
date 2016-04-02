package itemWindow;

import java.awt.Toolkit;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ItemWindow {
	private TabPane myTabPane;
	private static final int myScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int myScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	public ItemWindow(){
		myTabPane = new TabPane();
		Tab tab = new Tab();
		tab.setText("First Tab");
		tab.setContent(new Rectangle(0.3*myScreenWidth,myScreenHeight, Color.WHITE));
		myTabPane.getTabs().add(tab);
	}
	
	public TabPane getTabPane(){
		return myTabPane;
	}

}
