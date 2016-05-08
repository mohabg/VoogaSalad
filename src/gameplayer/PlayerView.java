// This entire file is part of my masterpiece.
// HUIJIA YU
//This class displays the game. It implements both
//an abstract superclass and an interface. This class effectively
//encapsulates the implementation of the view of a level-
//based game engine. It has no dependencies, and is closed, which 
//would allow it to easily be used in a different project.

package gameplayer;

import highscoretable.HighScoreController;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerView extends Screen implements IView {
	private Map<Number, Map<Integer, Node>> myViewSpriteMap;
	private Map<Integer, Node> myViewSprites;
	private Map<Number, StringProperty> myBackgrounds;

	public PlayerView() {
		super();
		myViewSpriteMap = new HashMap<Number, Map<Integer, Node>>();
		myViewSprites = new HashMap<Integer, Node>();
		myBackgrounds = new HashMap<Number, StringProperty>();
		
		myPane.getChildren().add(new Button(""));
	}

	@Override
	public void setActiveSprites(List<Integer> activeSprites) {
		this.clearSprites();
		activeSprites.stream().forEach(s -> {
			Node vs = myViewSprites.get(s);
			if (vs != null) {
				myPane.getChildren().add(0, vs);
			}
		});
	}

	@Override
	public void setBackgroundList(Number id, StringProperty background) {
		myBackgrounds.put(id, background);
	}

	public void setBackground(Number id) {
		StringProperty backgroundName = myBackgrounds.get(id);
		try{
			myPane.setBackground(
					new Background(new BackgroundImage(new Image(backgroundName.get()), BackgroundRepeat.REPEAT,
							BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		} 
		catch(Exception e){
			
		}
	}

	public Map<Integer, Node> getViewSprites() {
		return myViewSprites;
	}

	public void makeHighScoreTable() {
		HighScoreController hsc = new HighScoreController();
		myPane.getChildren().clear();
		myPane.getChildren().add(hsc.getTable());

	}

	@Override
	public void selectLevelSprites(Number levelID) {
		myViewSprites = myViewSpriteMap.get(levelID);

	}

	public void clearSprites() {
		myPane.getChildren().removeAll(myViewSprites.values());
	}

	@Override
	public void setLevelSprites(Number levelID, Map<Integer, Node> map) {
		myViewSpriteMap.put(levelID, map);

	}

}
