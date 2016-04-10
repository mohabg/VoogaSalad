package HUD;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HeadsUpDisplay {
	private BorderPane myHUD;
	private Map<HUDEnum, HUDElement> displayPanes;
	
	// TODO: unless i have no other use for HUDElement, i should probably take away the wrapper or have hudelement extend pane.. it's getting annoying
	
	public HeadsUpDisplay() {
		myHUD = new BorderPane();
		makeDisplay();
	}
	
	private void makeDisplay() {
		displayPanes = new HashMap<HUDEnum, HUDElement>();
		
		for(HUDEnum boxType : HUDEnum.values()) {
			HUDElement newBox = new HUDElement(boxType);
			createHUD(boxType, newBox.getBox());
			displayPanes.put(boxType, newBox);
		}
	}
	
	private void createHUD(HUDEnum loc, Pane newPane) {
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
		}
	}
		
	
	/**
	 * 
	 * @param loc
	 * @param items
	 */
	public void addToHUDElement(HUDEnum loc, Node... items) {
		HUDElement hudBar = displayPanes.get(loc);
		
		for (Node item : items) {
			hudBar.addHUDItem(item);
		}
		
		displayPanes.put(loc, hudBar);
	}
	
	/**
	 * 
	 * @param loc
	 * @param items
	 */
	public void removeFromHUDElement(HUDEnum loc, Node... items) {
		HUDElement hudBar = displayPanes.get(loc);
		
		for (Node item : items) {
			hudBar.removeHUDItem(item);
		}
		
		displayPanes.put(loc, hudBar);
	}
	
	public void setHeightHUDElement(HUDEnum loc, double height) {
		HUDElement hudBar = displayPanes.get(loc);		
		Pane hudBarBox = hudBar.getBox();		
		hudBarBox.setPrefHeight(height);
	}
	
	public void setWidthHUDElement(HUDEnum loc, double width) {
		HUDElement hudBar = displayPanes.get(loc);		
		Pane hudBarBox = hudBar.getBox();		
		hudBarBox.setPrefHeight(width);
	}
	
	public void resizeHUDElement(HUDEnum loc, double width, double height) {
		HUDElement hudBar = displayPanes.get(loc);		
		Pane hudBarBox = hudBar.getBox();		
		hudBarBox.resize(width, height);
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public BorderPane getHUD() {
		return myHUD;
	}
}
