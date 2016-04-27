package level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import Physics.PhysicsEngine;
import behaviors.Attack;
import behaviors.Behavior;
import behaviors.IActions;
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import collisions.EnemyCollision;
import gameElements.Actions;
import events.EventManager;
import events.Executable;
import events.InputHandler;
import gameElements.Score;
import gameElements.Sprite;
import gameElements.SpriteMap;
import gameElements.Time;
import gameplayer.SpriteFactory;
import goals.Goal;
import goals.Goal.Goals;
import goals.GoalChecker;
import goals.GoalFactory;
import goals.GoalProperties;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import keyboard.IKeyboardAction;
import keyboard.IKeyboardAction.KeyboardActions;
import keyboard.KeyboardActionChecker;
import keyboard.KeyboardActionFactory;

import java.util.*;

/**
 * This is the class for level in the game. It has spriteMap, which is a map of
 * Integer(spriteIDs) to Sprites. Any time someone wants the program to know a
 * sprite in a level exists, it must be added to spriteMap GoalMap is similar,
 * whenever a goal for a level is set for a level, it must be added to the
 * goalMap(which also is a map of Integer (goalIDs) to Goals. The
 * CurrentSpriteID is the Sprite that will be currently affected by actions of
 * the program. The goalCount is describing how many goals for a level exist.
 *
 * @see LevelProperties
 */

public class Level implements ILevel {
	private LevelProperties levelProperties;
	private Time time;
	private SpriteMap spriteMap;
	private List<Goal> goalList;
	private Map<KeyboardActions, IKeyboardAction> keyboardActionMap;
	private PhysicsEngine physicsEngine;
	private Integer userControlledSpriteID;
	private Integer currentSpriteID;
	private GoalFactory goalFactory;
	private int goalCount;
	private boolean isFinished;
	private Actions actions;
	private EventManager myEventManager;

	public Level() {

		levelProperties = new LevelProperties();
		physicsEngine = new PhysicsEngine(0.9);
		spriteMap = new SpriteMap();
		goalList = new ArrayList<Goal>();
		keyboardActionMap = new HashMap<KeyboardActions, IKeyboardAction>();
		goalFactory = new GoalFactory();
		goalCount = 0;
		isFinished = false;
		currentSpriteID = 0;

		actions = new Actions();

		myEventManager = new EventManager();
		myEventManager.setCollisionHandler(new CollisionHandler());
		myEventManager.setInputHandler(new InputHandler());
		populateGoals();
	}
	
	public List<Goal> getGoalList() {
		return goalList;
	}

	public void setGoalList(List<Goal> goalList) {
		this.goalList = goalList;
	}

	public LevelProperties getLevelProperties() {
		return levelProperties;
	}

	public void setisFinished(boolean finished) {
		isFinished = finished;
	}

	public boolean getisFinished() {
		return isFinished;
	}

	public void setLevelProperties(LevelProperties levelProperties) {
		this.levelProperties = levelProperties;
	}

	public SpriteMap getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(Map<Integer, Sprite> spriteMap) {
		this.spriteMap.setSpriteMap(spriteMap);
	}

	public Integer getCurrentSpriteID() {
		return currentSpriteID;
	}

	public void setCurrentSpriteID(Integer currentSpriteID) {
		this.currentSpriteID = currentSpriteID;
		Sprite currentSprite = spriteMap.get(currentSpriteID);
		myEventManager.setSpriteActions(currentSprite.getUserPressBehaviors());
	}

	public void deleteSprite(Integer spriteID) {
		getSpriteMap().remove(spriteID);
	}

	public Integer newSpriteID(SpriteMap spriteMap2) {
		while (spriteMap2.getSpriteMap().keySet().contains(currentSpriteID)) {
			currentSpriteID++;
		}
		return currentSpriteID;
	}

	/**
	 * @param newSprite
	 *            gets newSprite and adds it to the sprite map(adding it to the
	 *            current level)
	 */

	public void addSprite(Sprite newSprite) {

		spriteMap.addSprite(newSprite);
		setCurrentSpriteID(spriteMap.getLastSpriteID());
		if (newSprite.isUserControlled()) {
			userControlledSpriteID = spriteMap.getLastSpriteID();
		}
		// return new ID??
		// checking for whether it is the main character-->should be done
		// through the states pattern
	}

	/**
	 * @param newSprite
	 *            Sprite who's id you want to update
	 * @param spriteID
	 *            the new ID you want your sprite to be considered
	 */
	public void updateSpriteID(Integer spriteID, Sprite newSprite) {
		getSpriteMap().getSpriteMap().put(spriteID, newSprite);
	}

	public int getCurrentPoints() {
		return getScore().intValue();

	}

	public GoalFactory getGoalFactory() {
		return goalFactory;
	}

	public void setGoalFactory(GoalFactory goalFactory) {
		this.goalFactory = goalFactory;
	}

	public void deleteGoal(Goal goal) {
		goalList.remove(goal);
		if (levelProperties.getNumGoals() > goalList.size()) {
			levelProperties.setNumGoals(levelProperties.getNumGoals() - 1);
		}
	}

	public void addGoal(Goal goal) {
		goalList.add(goal);
	}

	public IntegerProperty getScore() {
		return levelProperties.getScore();
	}

	private void populateGoals() {
		// System.out.println("gaolpropertysize"+getLevelProperties().getGoalProperties().size());
		for (GoalProperties property : getLevelProperties().getGoalProperties()) {
			// System.out.println(property.getGoalName());
			goalList.add(goalFactory.makeGoal(property));
		}
		// System.out.println("populate goals"+goalList.size());
		
		
		
	}

	private boolean completeGoals() {
		GoalChecker goalChecker = new GoalChecker(this);
		List<Goal> deleteGoals= new ArrayList<Goal>();
		for (Goal goal : goalList) {
			goal.acceptVisitor(goalChecker);
			if (goal.isFinished()){
				goalCount++;
				deleteGoals.add(goal);
			}
		}
		goalList.removeAll(deleteGoals);
		return goalCount >= getLevelProperties().getNumGoals();
	}

	private void updateSprites() {

		List<Integer> spriteList = new ArrayList<Integer>();
		List<Integer> spriteIDList = new ArrayList<Integer>(spriteMap.getSpriteMap().keySet());
		if (spriteMap.getSpriteMap().isEmpty()) {
			System.out.println();
		}
		for (Integer spriteID : spriteIDList) {
			Sprite sprite=spriteMap.get(spriteID);
			this.actions.setSprite(sprite);
			sprite.update(this.actions);
			this.getPhysicsEngine().updateSprite(sprite);
			if (!sprite.isUserControlled()
					&& sprite.getBehaviors().get("default") != null) {
				sprite.getBehaviors().get("default").apply(actions);
			}
			removeDeadSprite(spriteID, spriteList);
		}
	}

	private void removeDeadSprite(Integer spriteID, List<Integer> deadSpriteList) {
		if (spriteMap.get(spriteID).isDead()) {
			spriteMap.remove(spriteID);
		}

	}

	private void checkCollisions() {
		myEventManager.checkCollisions(spriteMap, getLevelProperties());
	}

	private void handleKeyboardAction(KeyEvent key, boolean enable) {
		System.out.println(key.getCode() + key.getCharacter());
		levelProperties.addScore(10);
		KeyboardActions action = getLevelProperties().getKeyboardAction(key.getCode());
		IKeyboardAction keyboardAction = keyboardActionMap.get(action);

		Sprite currentSprite = getSpriteMap().get(userControlledSpriteID);
		if (currentSprite == null) {
			return;
		}
		if (currentSprite.isUserControlled()) {
			Executable execute = currentSprite.getUserPressBehavior(key.getCode());
			System.out.println(key.getCode() + "keycode");
			System.out.println(execute.toString());
			System.out.println("angle"+currentSprite.getAngle());
			System.out.println("xVel, x" + " " + currentSprite.getX() +" " + currentSprite.getSpriteProperties().getMyXvel());
			System.out.println("yVel, y" + " " + currentSprite.getY() + " " + currentSprite.getSpriteProperties().getMyYvel());
			if (execute != null && execute instanceof Behavior) {
				Behavior behavior = (Behavior) execute;
				if (enable) {
					behavior.enable();
				} else {
					behavior.disable();
				}
			}

		} else {
			if (keyboardAction == null) {
				keyboardAction = KeyboardActionFactory.buildKeyboardAction(action);
				keyboardActionMap.put(action, keyboardAction);
			}

			KeyboardActionChecker keyboardActionChecker = new KeyboardActionChecker();

			if (keyboardActionChecker.checkKeyboardAction(action, currentSprite) && enable) {
				keyboardAction.enableKeyboardAction(currentSprite);
			} else {
				keyboardAction.disableKeyboardAction(currentSprite);
			}

		}
	}

	@Override
	public void update() {
		updateSprites();
		checkCollisions();
		setisFinished(completeGoals());
		System.out.println("FINAL RESULT" + getisFinished());

	}

	/**
	 * This method handles Key Press Events.
	 */
	public void handleKeyPress(KeyEvent key) {
		//handleKeyboardAction(key, true);
		myEventManager.keyEvent(key, actions);
	}

	public void setSpriteFactory(SpriteFactory mySpriteFactory) {
		this.actions.setSpriteFactory(mySpriteFactory);
	}

	public Sprite getCurrentSprite() {
		return spriteMap.get(currentSpriteID);
	}
	
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}


	public PhysicsEngine getPhysicsEngine() {
		return physicsEngine;
	}


	public void setPhysicsEngine(PhysicsEngine physicsEngine) {
		this.physicsEngine = physicsEngine;
	}

}
