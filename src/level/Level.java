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
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import collisions.EnemyCollision;
import events.EventManager;
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
	private SpriteFactory mySpriteFactory;
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
		myEventManager = new EventManager();
	//	System.out.println("goalListSize"+ goalList.size());
	//	System.out.println("level constructor find numgoals" + levelProperties.getNumGoals());
		populateGoals();
		// System.out.println("goalListSize"+ goalList.size());

	}
	// public Level()

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
		/*
		 * Integer newSpriteID = newSpriteID(spriteMap);
		 * setCurrentSpriteID(newSpriteID); getSpriteMap().put(newSpriteID,
		 * newSprite);
		 */

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

		// return getLevelProperties().getScore().getScoreValue().intValue();
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

	// public Score getScore(){
	// return levelProperties.getScore();
	// }
	private void populateGoals() {
		// System.out.println("gaolpropertysize"+getLevelProperties().getGoalProperties().size());
		for (GoalProperties property : getLevelProperties().getGoalProperties()) {
			// System.out.println(property.getGoalName());
			goalList.add(goalFactory.makeGoal(property));
		}
		// System.out.println("populate goals"+goalList.size());
		
		
		
	}

	private boolean completeGoals() {
		//System.out.println("gothere" + goalList.size());

		GoalChecker goalChecker = new GoalChecker(this);
	//  System.out.println("goalCount" + " " + getLevelProperties().getNumGoals());
	//	System.out.println("gothere");
		List<Goal> deleteGoals= new ArrayList<Goal>();
		for (Goal goal : goalList) {
			//System.out.println("hit");
		// System.out.println(goalList.size());
		//	System.out.println(goal.getGoalProperties().getGoalName());
			goal.acceptVisitor(goalChecker);
		//	System.out.println("comes here");
			if (goal.isFinished()){
				System.out.println("goal is finished");
				goalCount++;
				deleteGoals.add(goal);
			}
		}
		goalList.removeAll(deleteGoals);
		
		System.out.println(goalCount + "goalCount");
		
		System.out.println("num goals"+getLevelProperties().getNumGoals());
		System.out.println(goalCount >= getLevelProperties().getNumGoals());
		return goalCount >= getLevelProperties().getNumGoals();
	}

	// private List<Integer> updateSprites() {
	private void updateSprites() {
		// System.out.println("updatesprite" + goalList.size());

		List<Integer> spriteList = new ArrayList<Integer>();
		List<Integer> spriteIDList = new ArrayList<Integer>(spriteMap.getSpriteMap().keySet());
		if (spriteMap.getSpriteMap().isEmpty()) {
			System.out.println();
		}
		for (Integer spriteID : spriteIDList) {
			spriteMap.get(spriteID).update(this.mySpriteFactory);
			if (!spriteMap.get(spriteID).isUserControlled()
					&& spriteMap.get(spriteID).getBehaviors().get("default") != null) {
				spriteMap.get(spriteID).getBehaviors().get("default").apply(spriteMap.get(spriteID), mySpriteFactory);
			}

			removeDeadSprite(spriteID, spriteList);
		}
		// return spriteList;
	}

	private void removeDeadSprite(Integer spriteID, List<Integer> deadSpriteList) {
		if (spriteMap.get(spriteID).isDead()) {
			spriteMap.remove(spriteID);
			// deadSpriteList.add(spriteID);
		}

	}

	private void checkCollisions() {

		CollisionHandler collisionHandler = new CollisionHandler();
		CollisionChecker checker = new CollisionChecker();
		Collection<Sprite> spriteSet = spriteMap.getSpriteMap().values();
		Sprite[] spriteArr = new Sprite[spriteSet.size()];
		int index = 0;
		for (Sprite sprite : spriteSet) {
			spriteArr[index] = sprite;
			index++;
		}

		for (int i = 0; i < spriteSet.size(); i++) {
			for (int j = i + 1; j < spriteSet.size(); j++) {
				if (checker.areColliding(spriteArr[i], spriteArr[j])) {

					getLevelProperties().setCollidingSprites(spriteArr[i], spriteArr[j]);

					for (Collision collisionSpriteOne : spriteArr[i].getCollisions()) {
						for (Collision collisionSpriteTwo : spriteArr[j].getCollisions()) {
							collisionHandler.applyCollision(collisionSpriteOne, collisionSpriteTwo,
									getLevelProperties());

						}

					}
				}
			}
		}
	}

	private void handleKeyboardAction(KeyEvent key, boolean enable) {
		System.out.println(key.getCode() + key.getCharacter());
		//System.out.println("goal list keyboard" + goalList.size());
		// System.out.println(goalList.get(0).getGoalProperties().getTotalPoints()+
		// " "+goalList.get(0).getGoal().name());
		levelProperties.addScore(10);
		KeyboardActions action = getLevelProperties().getKeyboardAction(key.getCode());
		IKeyboardAction keyboardAction = keyboardActionMap.get(action);

		Sprite currentSprite = getSpriteMap().get(userControlledSpriteID);
		if (currentSprite == null) {
			return;
		}
		// System.out.println("X: " + currentSprite.getX().doubleValue());
		// System.out.println("Y: " + currentSprite.getY().doubleValue());
		// System.out.println("HEALTH:
		// "+currentSprite.getHealth().getHealthValue());

		if (currentSprite.isUserControlled()) {
			Behavior behavior = currentSprite.getUserPressBehavior(key.getCode());
			if (behavior != null) {
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
	// public List<Integer> update() {
	public void update() {
//		System.out.println("isFinishedOG" + getisFinished());

	//	System.out.println("update" + goalList.size());
		updateSprites();
		checkCollisions();
		setisFinished(completeGoals());
		System.out.println("FINAL RESULT" + getisFinished());
//		System.out.println("completeGoals" + completeGoals());
//		System.out.println("isFinished" + getisFinished());
		// return deadSprites;

	}

	/**
	 * This method handles Key Press Events.
	 */
	public void handleKeyPress(KeyEvent key) {
		handleKeyboardAction(key, true);
	}

	/**
	 * This method handles Key Release Events.
	 */
	public void handleKeyRelease(KeyEvent key) {
		handleKeyboardAction(key, false);
	}

	public void setSpriteFactory(SpriteFactory mySpriteFactory) {
		this.mySpriteFactory = mySpriteFactory;
	}

	public Sprite getCurrentSprite() {
		// TODO Auto-generated method stub
		return spriteMap.get(currentSpriteID);
	}
	
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
