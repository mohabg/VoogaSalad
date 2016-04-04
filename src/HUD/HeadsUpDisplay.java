package HUD;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class HeadsUpDisplay {
	
	Map<HUDEnum, HUDElement> displayPanes;
	
	public HeadsUpDisplay() {
		makeDisplay();
	}
	
	private void makeDisplay() {
		displayPanes = new HashMap<HUDEnum, HUDElement>();
		
		for(HUDEnum val : HUDEnum.values()) {
			displayPanes.put(val, new HUDElement(val));
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
}
