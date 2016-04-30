package interfaces;

import java.util.List;

import authoringEnvironment.LevelModel;
import authoringEnvironment.mainWindow.GameAuthoringTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public interface ITabPane {
	void addNewTab();
	GameAuthoringTab getCurrentTab();	
	List<GameAuthoringTab> getTabs();
	TabPane getTabPane();
}
