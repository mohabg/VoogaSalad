package gameplayer;

import authoringEnvironment.RefObject;
import authoringEnvironment.ViewSprite;
import behaviors.Behavior;
import behaviors.Gun;
import behaviors.MoveVertically;
import collisions.Collision;
import events.Executable;
import gameElements.Health;
import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteMap;
import gameElements.SpriteProperties;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteFactory {
	//NEEDS SIGNIFICANT REFACTORING
	private Map<Integer, ViewSprite> myViewSprites;
	private SpriteMap spriteMap;
	
	public SpriteFactory(Map<Integer, ViewSprite> myViewSprites, SpriteMap mySpriteMap ) {
		this.myViewSprites = myViewSprites;
		this.spriteMap = mySpriteMap;
	}

	public SpriteMap getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(SpriteMap spriteMap) {
		this.spriteMap = spriteMap;
	}
	public Map<Integer, ViewSprite> getMyViewSprites(){
		return this.myViewSprites;
	}
	public Sprite makeSprite(double x, double y, RefObject myRef){
		return makeSprite(x, y, new Health(), new ArrayList<>(), new HashMap<>(), myRef);
	}

	public Sprite makeSprite(double x, double y, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		//TEMPORARY
		ViewSprite vs = new ViewSprite(myRef.getMyRef());
		ISpriteProperties sp = vs.getMySpriteProperties();
		ObservableList<Collision> ol = FXCollections.observableList(myCollisions);
		ListProperty<Collision> collisions = new SimpleListProperty<Collision>(ol);
		ObservableMap<StringProperty, Behavior> om1 = FXCollections
				.observableMap(new HashMap<StringProperty, Behavior>());
		MapProperty<StringProperty, Behavior> behaviors = new SimpleMapProperty<StringProperty, Behavior>(om1);
		Sprite sprite = createAndBindSprite(myHealth, collisions, behaviors, myRef, vs, sp);
		sprite.getSpriteProperties().setX(x);
		sprite.getSpriteProperties().setY(y);
		this.addToMap(vs, sprite);
		return sprite;
	}

	public Sprite makeSprite(double x, double y, ISpriteProperties clone, RefObject myRef) {
		ViewSprite vs = new ViewSprite(myRef.getMyRef());
		double imageWidth = vs.getMySpriteProperties().getWidth();
		double imageHeight = vs.getMySpriteProperties().getHeight();
		clone.setWidth(imageWidth);
		clone.setHeight(imageHeight);
		ObservableList<Collision> ol = FXCollections.observableList(new ArrayList<Collision>());
		ListProperty<Collision> myCollisions = new SimpleListProperty<Collision>(ol);
		ObservableMap<StringProperty, Behavior> om1 = FXCollections
				.observableMap(new HashMap<StringProperty, Behavior>());
		MapProperty<StringProperty, Behavior> behaviors = new SimpleMapProperty<StringProperty, Behavior>(om1);

		return createAndBindSprite(new Health(), myCollisions, behaviors, myRef, vs, clone);
	}
	
	public Sprite clone(ISprite sprite){
		ISpriteProperties clonedProperties = sprite.getSpriteProperties().getClone();
		List<Collision> clonedCollisions = new ArrayList<>();
		for(Collision col : sprite.getCollisions()){
			clonedCollisions.add(col.clone());
		}
		ObservableList<Collision> ol = FXCollections.observableList(clonedCollisions);
		ListProperty<Collision> myCollisions = new SimpleListProperty<Collision>(ol);
		ObservableMap<StringProperty, Behavior> om2 = FXCollections.observableMap(new HashMap<StringProperty, Behavior>());
		
		MapProperty<StringProperty, Behavior> map = new SimpleMapProperty<StringProperty, Behavior>(om2);
		ViewSprite vs = new ViewSprite(sprite.getMyRef());
		Sprite clone = this.createAndBindSprite(sprite.getMyHealth().getClone(), myCollisions, map, 
				new RefObject(sprite.getMyRef()), vs, clonedProperties);
		this.addToMap(vs, clone);
		return clone;
	}
	
	private Sprite createAndBindSprite(Health myHealth, ListProperty<Collision> myCollisions, MapProperty<StringProperty, Behavior> myBehaviors,
			RefObject myRef, ViewSprite vs, ISpriteProperties sp) {
		Sprite s = new Sprite(sp, myHealth, myCollisions , myBehaviors, myRef);
		vs.bindToSprite(s);
		return s;
	}

	private void addToMap(ViewSprite vs, Sprite s) {
		myViewSprites.put(spriteMap.getCurrentID()+1, vs);
		spriteMap.addSprite(s);
	}
}