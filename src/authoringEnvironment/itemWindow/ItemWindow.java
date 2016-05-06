// This entire file is part of my masterpiece.
// Sam Toffler

// ItemWindow.java is a class that holds the ItemTabs that the user creates.
// Please see ItemTab.java for a more detailed explanation about the changes
// and benefits of my masterpiece. ItemWindow.java could be placed in a window
// or straight inside the program, which is part of it's new flexibility.
// For a very basic idea of how to use my masterpiece, please see 
// MainAuthoringWindow.java. 

package authoringEnvironment.itemWindow;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class ItemWindow {
	private TabPane myTabPane;

	public ItemWindow() {
		myTabPane = new TabPane();
		myTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
	
	public void addNewTab(Tab tab){
		myTabPane.getTabs().add(tab);
	}
	
	public TabPane getTabPane(){
		return myTabPane;
	}
}