package interfaces;

import authoringEnvironment.ViewSprite;
import gameElements.Sprite;
import javafx.scene.Node;
import javafx.scene.control.Tab;

public interface ITab {
	void setTabContent(Node content);
	
//	void setTabContent(ViewSprite view, Sprite mySprite);
	
	Node getTabContent();
	
	Tab getTab();
	
	void setTabTitle(String tabTitle);

	void setViewSprite(ViewSprite view);
}
