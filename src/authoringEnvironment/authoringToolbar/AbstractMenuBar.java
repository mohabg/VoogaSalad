package authoringEnvironment.authoringToolbar;

import authoringEnvironment.MainAuthoringWindow;
import interfaces.ITabPane;
import javafx.scene.control.MenuBar;
import resources.FrontEndData;

public abstract class AbstractMenuBar {
	protected MenuBar myMenuBar;
	protected String myName;

	// TODO SWITCH TO REFLECTION

	public AbstractMenuBar(String gameName) {
		myMenuBar = new MenuBar();
		myName = gameName;
	}

	public MenuBarElement getBackMenu(MainAuthoringWindow mainAuthoringWindow) {
		MenuBarElement myBackMenu = new MenuBarElement();
		myBackMenu.setName(FrontEndData.ButtonLabels.getString("BackMenu"));
		myBackMenu.setNewAction(FrontEndData.ButtonLabels.getString("BackMenu1"), e -> {
			mainAuthoringWindow.returnToStart();
		});
		return myBackMenu;
	}

	public MenuBar getMenuBar() {
		return myMenuBar;
	}

}
