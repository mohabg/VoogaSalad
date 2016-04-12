package interfaces;

import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import javafx.scene.Node;
import javafx.scene.control.Tab;

public interface ITab {
	void setTabContent(Node content);
	
	void setTabContent(ViewSprite view, Model model);
	
	Node getTabContent();
	
	Tab getTab();
	
	void setTabTitle(String tabTitle);
}
