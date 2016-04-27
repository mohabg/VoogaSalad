package level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Physics.PhysicsEngine;
import behaviors.Behavior;
import collisions.CollisionHandler;
import gameElements.Actions;
import events.EventManager;
import gameElements.Sprite;
import gameElements.SpriteMap;
import gameplayer.SpriteFactory;
import goals.Goal;
import goals.GoalChecker;
import goals.GoalFactory;
import goals.GoalProperties;
import javafx.beans.property.IntegerProperty;
import javafx.scene.input.KeyEvent;
import keyboard.IKeyboardAction;
import keyboard.IKeyboardAction.KeyboardActions;
import keyboard.KeyboardActionChecker;
import keyboard.KeyboardActionFactory;



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
	private Map<KeyboardActions, IKeyboardAction> keyboardActionMap;
	private PhysicsEngine physicsEngine;
	private GoalFactory goalFactory;
	private Actions actions;
	private EventManager myEventManager;
	
	public Level() {

		levelProperties = new LevelProperties();
		physicsEngine = new PhysicsEngine(0.9);
		keyboardActionMap = new HashMap<KeyboardActions, IKeyboardAction>();
		goalFactory = new GoalFactory();

		actions = new Actions();

		myEventManager = new EventManager();
		myEventManager.setCollisionHandler(new CollisionHandler());
		populateGoals();

	}
	
	public List<Goal> getGoalList() {
		return this.levelProperties.getGoalList();
	}

	public void setGoalList(List<Goal> goalList) {
		this.levelProperties.setGoalList(goalList);
	}

	public LevelProperties getLevelProperties() {
		return this.levelProperties;
	}

	public void setisFinished(boolean isFinished) {
		this.getLevelProperties().setIsFinished(isFinished);
	}

	public boolean getisFinished() {
		return this.getLevelProperties().isFinished();
	}

	public void setLevelProperties(LevelProperties levelProperties) {
		this.levelProperties = levelProperties;
	}

	public SpriteMap getSpriteMap() {
		return this.levelProperties.getSpriteMap();
	}


	public Integer getCurrentSpriteID() {
		return this.getLevelProperties().getSpriteMap().getCurrentID();
	}

	public void setCurrentSpriteID(Integer currentSpriteID) {
		this.getLevelProperties().getSpriteMap().getCurrentID();
	}

	public void deleteSprite(Integer spriteID) {
		getSpriteMap().remove(spriteID);
	}

	public Integer newSpriteID(SpriteMap spriteMap2) {
		Integer currentSpriteID = this.getLevelProperties().getSpriteMap().getCurrentID();
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
		SpriteMap spriteMap = this.levelProperties.getSpriteMap();
		spriteMap.addSprite(newSprite);
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
		getSpriteMap().put(spriteID, newSprite);
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
		List<Goal> goalList = this.levelProperties.getGoalList();
		goalList.remove(goal);
		if (levelProperties.getNumGoals() > goalList.size()) {
			levelProperties.setNumGoals(levelProperties.getNumGoals() - 1);
		}
	}

	public void addGoal(Goal goal) {
		this.levelProperties.getGoalList().add(goal);
	}

	public IntegerProperty getScore() {
		return levelProperties.getScore();
	}

	private void populateGoals() {
		// System.out.println("gaolpropertysize"+getLevelProperties().getGoalProperties().size());
		for (GoalProperties property : getLevelProperties().getGoalProperties()) {
			// System.out.println(property.getGoalName());
			this.getGoalList().add(goalFactory.makeGoal(property));
		}
		// System.out.println("populate goals"+goalList.size());
		
		
		
	}

	private boolean completeGoals() {
		GoalChecker goalChecker = new GoalChecker(this);
		List<Goal> deleteGoals= new ArrayList<Goal>();
		List<Goal> goalList = this.levelProperties.getGoalList();
		int goalCount = this.getLevelProperties().getGoalCount();
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
		SpriteMap spriteMap = this.levelProperties.getSpriteMap();
		List<Integer> spriteList = new ArrayList<Integer>();
		List<Integer> spriteIDList = new ArrayList<Integer>(spriteMap.getSpriteMap().keySet());
		if (spriteMap.getSpriteMap().isEmpty()) {
//			System.out.println();
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
		SpriteMap spriteMap = this.levelProperties.getSpriteMap();
		if (spriteMap.get(spriteID).isDead()) {
			spriteMap.remove(spriteID);
		}

	}

	private void checkCollisions() {
		myEventManager.handleCollisions(getLevelProperties());
	}

	private void handleKeyboardAction(KeyEvent key, boolean enable) {
		System.out.println(key.getCode() + key.getCharacter());
		levelProperties.addScore(10);
		KeyboardActions action = getLevelProperties().getKeyboardAction(key.getCode());
		IKeyboardAction keyboardAction = keyboardActionMap.get(action);

		Sprite currentSprite = getSpriteMap().getCurrentSprite();
		if (currentSprite == null) {
			return;
		}
		if (currentSprite.isUserControlled()) {
			Behavior behavior = currentSprite.getUserPressBehavior(key.getCode());
			System.out.println(key.getCode() + "keycode");
			System.out.println(behavior.toString());
			System.out.println("angle"+currentSprite.getAngle());
			System.out.println("xVel, x" + " " + currentSprite.getX() +" " + currentSprite.getSpriteProperties().getMyXvel());
			System.out.println("yVel, y" + " " + currentSprite.getY() + " " + currentSprite.getSpriteProperties().getMyYvel());
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
	public void update() {
		updateSprites();
		checkCollisions();
		setisFinished(completeGoals());

	}

	/**
	 * This method handles Key Press Events.
	 */
	public void handleKeyPress(KeyEvent key) {
		//handleKeyboardAction(key, true);
		myEventManager.keyEvent(key);
	}

	public void setSpriteFactory(SpriteFactory mySpriteFactory) {
		this.actions.setSpriteFactory(mySpriteFactory);
	}

	public Sprite getCurrentSprite() {
		return this.levelProperties.getSpriteMap().getCurrentSprite();
	}

	public PhysicsEngine getPhysicsEngine() {
		return physicsEngine;
	}


	public void setPhysicsEngine(PhysicsEngine physicsEngine) {
		this.physicsEngine = physicsEngine;
	}

}
