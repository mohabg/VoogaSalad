package interfaces;

import java.util.List;

import javafx.scene.control.TabPane;

public interface ITabPane {
	void addNewTab(ITab newTab);
	
	TabPane getTabPane();
}
