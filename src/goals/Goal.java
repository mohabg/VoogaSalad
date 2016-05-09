package goals;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;

/**
 * Describes the goals for the level. 
 */


public abstract class Goal implements IGoal{
	@IgnoreField
	private GoalProperties goalProperties;
	
	public Goal(GoalProperties myProperties){
		setGoalProperties(myProperties);
	}
	
	public Goal(){
		goalProperties=new GoalProperties();
	}

	public GoalProperties getGoalProperties() {
		return goalProperties;
	}

	public void setGoalProperties(GoalProperties goalProperties) {
		this.goalProperties = goalProperties;
	}
	
	public void setFinished(){
		goalProperties.setFinished();
	}
	
	public void setIsFinished(boolean isFinished){
		goalProperties.setIsFinished(isFinished);
	}
	
	public Boolean isFinished(){
		return goalProperties.isFinished();
	}
	public GoalEnum getGoal(){
		return goalProperties.getMyGoal();
	}
	

}
