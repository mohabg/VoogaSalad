package gameElements;

public class LevelProperties {
	private Integer levelID;
	private String levelName;
	private Integer nextLevel;
	private Integer previousLevel;
	private Integer currentPoints;
	private Time time;
	private Integer numGoals;
	
	public LevelProperties(){
		setLevelID(null);
		setLevelName("");
		setNextLevel(-1);
		setPreviousLevel(-1);
	}
	public LevelProperties(Integer levelID, String levelName, Integer nextLevel, Integer previousLevel) {
		setLevelID(levelID);
		setLevelName(levelName);
		setNextLevel(nextLevel);
		setPreviousLevel(previousLevel);
	}
	public Integer getLevelID() {
		return levelID;
	}
	public void setLevelID(Integer levelID) {
		this.levelID = levelID;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Integer getNextLevel() {
		return nextLevel;
	}
	public void setNextLevel(Integer nextLevel) {
		this.nextLevel = nextLevel;
	}
	public Integer getPreviousLevel() {
		return previousLevel;
	}
	public void setPreviousLevel(Integer previousLevel) {
		this.previousLevel = previousLevel;
	}
	public Integer getCurrentPoints() {
		return currentPoints;
	}
	public void setCurrentPoints(Integer currentPoints) {
		this.currentPoints = currentPoints;
	}
	public Integer getNumGoals() {
		return numGoals;
	}
	public void setNumGoals(Integer numGoals) {
		this.numGoals = numGoals;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	
}
