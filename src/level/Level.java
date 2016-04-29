package level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Physics.PhysicsEngine;
import behaviors.Attack;
import behaviors.Behavior;
import behaviors.Defense;
import behaviors.Gun;
import behaviors.IActions;
import behaviors.MoveTurn;
import behaviors.Shield;
import behaviors.ThrustHorizontal;
import behaviors.ThrustVertical;
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import collisions.DamageCollision;
import collisions.DissapearCollision;
import collisions.EnemyCollision;
import gameElements.AIController;
import gameElements.Actions;
import gameElements.ISprite;
import gameElements.ISprite.spriteState;
import events.CollisionEvent;
import events.Event;
import events.EventManager;
import events.Executable;
import events.KeyPressEvent;
import events.KeyPressTrigger;
import events.Trigger;
import gameElements.Score;
import gameElements.Sprite;
import gameElements.SpriteMap;
import gameplayer.SpriteFactory;
import goals.Goal;
import goals.GoalChecker;
import goals.GoalFactory;
import goals.GoalProperties;
import goals.Goals;
import javafx.beans.property.IntegerProperty;
import javafx.scene.input.KeyCode;
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
	private AIController enemyController;
	
	public Level() {

		levelProperties = new LevelProperties();
		physicsEngine = new PhysicsEngine(0.9);
		keyboardActionMap = new HashMap<KeyboardActions, IKeyboardAction>();
		goalFactory = new GoalFactory();

		actions = new Actions();

		myEventManager = new EventManager();
		Event hardCodedEvent = new CollisionEvent("pictures/shootbullet.png", "pictures/black_ship.png", 
				new DamageCollision(10), new EnemyCollision());
		Event hardCodedEvent1 = new CollisionEvent("pictures/shootbullet.png", "pictures/black_ship.png", 
				new DissapearCollision(), new EnemyCollision());
		
		Event shooting = new KeyPressEvent(new KeyPressTrigger(KeyCode.SPACE),new Gun());
		
		Event defense = new KeyPressEvent(new KeyPressTrigger(KeyCode.SHIFT),new Shield());

		Event forward = new KeyPressEvent(new KeyPressTrigger(KeyCode.UP),new ThrustVertical(-1));

		Event reverse = new KeyPressEvent(new KeyPressTrigger(KeyCode.DOWN),new ThrustVertical(1));
		
		Event left = new KeyPressEvent(new KeyPressTrigger(KeyCode.LEFT), new ThrustHorizontal(-1));

		Event right = new KeyPressEvent(new KeyPressTrigger(KeyCode.RIGHT), new ThrustHorizontal(1));
		
		Event turnRight = new KeyPressEvent(new KeyPressTrigger(KeyCode.A), new MoveTurn(2));

		Event turnLeft = new KeyPressEvent(new KeyPressTrigger(KeyCode.D), new MoveTurn(358));
		
		myEventManager.addEvent(hardCodedEvent);
		//myEventManager.addEvent(hardCodedEvent1);
		myEventManager.addEvent(shooting);
		myEventManager.addEvent(defense);
		myEventManager.addEvent(forward);
		myEventManager.addEvent(reverse);
		myEventManager.addEvent(left);
		myEventManager.addEvent(right);
		myEventManager.addEvent(turnRight);
		myEventManager.addEvent(turnLeft);
		
		
		//myEventManager.setInputHandler(new InputHandler());
		populateGoals();
		System.out.println("levelproperties"+getLevelProperties().getGoalList());
	}
	
	public void addEvent(Event e) {
		myEventManager.addEvent(e);
	}
	
	@Override
	public void update() {
		updateSprites();
		myEventManager.doEvents(actions,getLevelProperties());
		setisFinished(completeGoals());

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
		levelProperties.addSpriteType(newSprite);
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
	

	private void populateGoals() {
		if(getLevelProperties().getGoalProperties().size()==0) getLevelProperties().addGoal(new GoalProperties());
		for (GoalProperties property : getLevelProperties().getGoalProperties()) {
			this.getGoalList().add(goalFactory.makeGoal(property));
		}
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
		for (Integer spriteID : spriteIDList) {
			ISprite sprite = spriteMap.get(spriteID);
			this.actions.setSprite(sprite);
			sprite.update(this.actions);
			this.getPhysicsEngine().updateSprite(sprite);
			if(sprite.isOutOfBounds() && !spriteIsHero(sprite)){
				//Temporary to avoid lagging
				sprite.kill();
			}
			removeDeadSprite(spriteID, spriteList);
		}
	}
	private boolean spriteIsHero(ISprite sprite){
		return this.levelProperties.getUserControlledSprite().equals(sprite);
	}
	private void removeDeadSprite(Integer spriteID, List<Integer> deadSpriteList) {
		SpriteMap spriteMap = this.levelProperties.getSpriteMap();
		if (spriteMap.get(spriteID).isDead()) {
			spriteMap.remove(spriteID);
		}

	}

	/**
	 * This method handles Key Press Events.
	 */
	public void handleKeyPress(KeyEvent key) {
		actions.setSprite(this.levelProperties.getSpriteMap().getUserControlledSprite());
		myEventManager.keyPress(key.getCode(), actions, this.levelProperties);
	}
	
	public void handleKeyRelease(KeyEvent key){
		actions.setSprite(this.levelProperties.getSpriteMap().getUserControlledSprite());
		myEventManager.keyRelease(key.getCode(), actions, this.levelProperties);
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

	public void setSpriteFactory(SpriteFactory mySpriteFactory) {
		this.actions.setSpriteFactory(mySpriteFactory);
	}

	public ISprite getCurrentSprite() {
		return this.levelProperties.getSpriteMap().getCurrentSprite();
	}

	public PhysicsEngine getPhysicsEngine() {
		return physicsEngine;
	}


	public void setPhysicsEngine(PhysicsEngine physicsEngine) {
		this.physicsEngine = physicsEngine;
	}

	public EventManager getMyEventManager() {
		return myEventManager;
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

	/*public void setSpriteActions() {
		myEventManager.setSpriteActions(levelProperties.getSpriteMap().getCurrentSprite().getUserPressBehaviors());
	}*/
	
	public void setCurrentSpriteID(Integer sprite) {
		this.getLevelProperties().getSpriteMap().setUserControlledSpriteID(sprite);
		//setSpriteActions();
	}

	public void setAIController(AIController enemyController) {
		this.enemyController = enemyController;
}
	public void setEvents(List<Event> myEvents) {
		this.myEventManager.setEvents(myEvents);
	}
}
