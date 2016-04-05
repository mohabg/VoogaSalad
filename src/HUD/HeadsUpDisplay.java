package HUD;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HeadsUpDisplay {
	BorderPane myHUD;
	Map<HUDEnum, HUDElement> displayPanes;
	
	public HeadsUpDisplay() {
		makeDisplay();
	}
	
	private void makeDisplay() {
		displayPanes = new HashMap<HUDEnum, HUDElement>();
		
		for(HUDEnum val : HUDEnum.values()) {
			HUDElement newEle = new HUDElement(val);
			addToMyHUD(val, newEle.getBox());
			displayPanes.put(val, newEle);
		}
	}
	
	private void addToMyHUD(HUDEnum loc, Pane newPane) {
		switch (loc) {
		case Left:
			myHUD.setLeft(newPane);
			break;
		case Right:
			myHUD.setRight(newPane);
			break;
		case Up:
			myHUD.setTop(newPane);
			break;
		case Down:
			myHUD.setBottom(newPane);
			break;
		default:
			// TODO: THROW ERROR
	}
	}
		
	public void addToHUDElement(HUDEnum loc, Node item) {
		HUDElement ele = displayPanes.get(loc);
		HUDItem newItem = new HUDItem(item);
		ele.addHUDItem(newItem);
		// TODO: CHECK IF ALREADY THERE?
		displayPanes.put(loc, ele);
	}
	
	public void removeFromHUDElement(HUDEnum loc, Node item) {
		HUDElement ele = displayPanes.get(loc);
		HUDItem oldItem = new HUDItem(item);
		ele.removeHUDItem(oldItem);
		// TODO: CHECK IF DOESNT EXIST????
		displayPanes.put(loc, ele);
	}
	
	public BorderPane getHUD() {
		return myHUD;
	}
}
