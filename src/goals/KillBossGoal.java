package goals;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KillBossGoal implements IGoal {
	private ListProperty<IntegerProperty> bossIDList;
	private GoalProperties goalProperties;

	public KillBossGoal(GoalProperties myProperties) {
		goalProperties=myProperties;
		setBossIDList();
	}
	
	public KillBossGoal(){
		goalProperties=new GoalProperties(GoalEnum.KillBossGoal);
		bossIDList = new SimpleListProperty<IntegerProperty>(FXCollections.<IntegerProperty>observableList(new ArrayList<IntegerProperty>()));
		setBossIDList();

	}

	public void setBossIDList() {
		setBossIDList(getGoalProperties().getTargetID());
	}

	public ListProperty<IntegerProperty> getBossIDList() {
		return bossIDList;
	}

	public void setBossIDList(ListProperty<IntegerProperty> bossIDList) {
		this.bossIDList=bossIDList;
	}
	
	@Override
	public void acceptVisitor(IGoalVisitor visitor){
		// System.out.println("acceptpointsvisitor");
		getGoalProperties().setIsFinished(visitor.visit(this));
	}
	

	public GoalProperties getGoalProperties() {
		return goalProperties;
	}

	public void setGoalProperties(GoalProperties goalProperties) {
		this.goalProperties = goalProperties;
	}

	@Override
	public Boolean isFinished() {
		return this.getGoalProperties().isFinished();
	}
	

}
