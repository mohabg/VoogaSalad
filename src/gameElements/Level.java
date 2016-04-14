package gameElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameElements.IKeyboardAction.KeyboardActions;
import javafx.scene.input.KeyEvent;

public class Level implements ILevel {
	private LevelProperties levelProperties;
	private Map<Integer, Sprite> spriteMap;
	private Map<Integer, Goal> goalMap;
	private Map<KeyboardActions, IKeyboardAction> keyboardActionMap;

	private Integer currentSpriteID;
	private GoalFactory goalFactory;
	private int goalCount;

	public Level() {
		keyboardActionMap = new HashMap<KeyboardActions, IKeyboardAction> ();

	}

	public Integer getLevelID() {
		return getLevelProperties().getLevelID();
	}

	public LevelProperties getLevelProperties() {
		return levelProperties;
	}

	public void setLevelProperties(LevelProperties levelProperties) {
		this.levelProperties = levelProperties;
	}

	public Map<Integer, Sprite> getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(Map<Integer, Sprite> spriteMap) {
		this.spriteMap = spriteMap;
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

	public Integer newSpriteID(Map spriteMap) {
		while (spriteMap.keySet().contains(currentSpriteID))
			currentSpriteID++;
		return currentSpriteID;
	}

	public void addSprite(Sprite newSprite) {
		Integer newSpriteID = newSpriteID(spriteMap);
		getSpriteMap().put(newSpriteID, newSprite);
		// return new ID??
		// checking for whether it is the main character-->should be done
		// through the states pattern
	}

	public void updateSpriteID(Integer spriteID, Sprite newSprite) {
		getSpriteMap().put(spriteID, newSprite);
	}

	public int getCurrentPoints() {
		return getLevelProperties().getCurrentPoints();
	}

	public GoalFactory getGoalFactory() {
		return goalFactory;
	}

	public void setGoalFactory(GoalFactory goalFactory) {
		this.goalFactory = goalFactory;
	}

	public void deleteGoal(Integer goalID) {
		goalMap.remove(goalID);
		if (levelProperties.getNumGoals() > goalMap.size()) {
			levelProperties.setNumGoals(levelProperties.getNumGoals() - 1);
		}
	}

	private boolean completeGoals() {
		GoalChecker goalChecker = new GoalChecker(this);
		for (Goal goal : goalMap.values()) {
			goal.acceptVisitor(goalChecker);
			if (goal.isFinished())
				goalCount++;
		}
		return goalCount >= getLevelProperties().getNumGoals();
	}

	private void updateSprites() {
		for (Sprite sprite : spriteMap.values()) {
			// sprite.update();
			removeDeadSprite(sprite);
		}
	}

	private void removeDeadSprite(Sprite sprite) {
		if (sprite.isDead())
			spriteMap.remove(sprite);

	}

	private void checkCollisions() {
		CollisionHandler collisionHandler = new CollisionHandler();
		CollisionChecker checker = new CollisionChecker();
		List<Sprite> spriteList = (ArrayList<Sprite>) spriteMap.values();

		for (int i = 0; i < spriteMap.values().size(); i++) {
			for (int j = i + 1; j < spriteMap.values().size(); j++) {

				if (checker.areColliding(spriteList.get(i), spriteList.get(j))) {

					for (Collision collisionSpriteOne : spriteList.get(i).getCollisions()) {
						for (Collision collisionSpriteTwo : spriteList.get(j).getCollisions()) {

							collisionHandler.applyCollision(collisionSpriteOne, collisionSpriteTwo);

						}

					}
				}
			}
		}
	}

	private void handleKeyboardAction(KeyEvent key, boolean enable) {
		KeyboardActions action = getLevelProperties().getKeyboardAction(key.getCode());
		System.out.println("KEYBOARD?");
		IKeyboardAction keyboardAction = keyboardActionMap.get(action);
		Integer currentSpriteID = getCurrentSpriteID();
		Actor currentSprite = (Actor) getSpriteMap().get(currentSpriteID);

		Behavior behavior = currentSprite.getBehavior(key);
		if (behavior != null) {
			behavior.apply(currentSprite);
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
		if (completeGoals()) {
			// TODO : win level
			// TODO : go 2 next level
		}
		// iterate through sprites and update them

		// check if sprites are dead
		// health has isDead method
		// remove sprite

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

}
