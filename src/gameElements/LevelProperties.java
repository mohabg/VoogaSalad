package gameElements;

public class LevelProperties {
	private Integer levelID;
	private String levelName;
	private Integer nextLevel;
	private Integer previousLevel;
	
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
	
	
}
