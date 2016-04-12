package interfaces;

import java.util.List;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public interface ITabPane {
	void addNewTab();
	
	ITab getCurrentTab();
	
	List<ITab> getITabs();
	
	TabPane getTabPane();
}
