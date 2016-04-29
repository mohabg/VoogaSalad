package goals;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KillBossGoal extends Goal implements IGoal {
	private ListProperty<IntegerProperty> bossIDList;

	public KillBossGoal(GoalProperties myProperties) {
		super(myProperties);
		setBossIDList();
	}
	
	public KillBossGoal(){
		super(new GoalProperties(Goals.KillBossGoal));
		bossIDList = new SimpleListProperty<IntegerProperty>(FXCollections.<IntegerProperty>observableList(new ArrayList<IntegerProperty>()));
		setBossIDList();
	//	List<Integer> list = new ArrayList<Integer>();
	//	ObservableList ol = FXCollections.observableList(list);
	//	bossIDList=new SimpleListProperty<Integer>(ol);
	}

	public void setBossIDList() {
		setBossIDList(getGoalProperties().getTargetID());
	}

	public ListProperty<IntegerProperty> getBossIDList() {
		return bossIDList;
	}

	public void setBossIDList(ListProperty<IntegerProperty> bossIDList) {
	//	ObservableList ol = FXCollections.observableList(bossIDList);
		this.bossIDList=bossIDList;
	}
	
	@Override
	public void acceptVisitor(IGoalVisitor visitor){
		// System.out.println("acceptpointsvisitor");
		setIsFinished(visitor.visit(this));
	}

}
