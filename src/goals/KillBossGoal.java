package goals;

import java.util.List;

public class KillBossGoal extends Goal implements IGoal {
	private List<Integer> bossIDList;

	public KillBossGoal(GoalProperties myProperties) {
		super(myProperties);
		setBossIDList();
	}

	public void setBossIDList() {
		bossIDList = getGoalProperties().getTargetID();
	}

	public List<Integer> getBossIDList() {
		return bossIDList;
	}

	public void setBossIDList(List<Integer> bossIDList) {
		this.bossIDList = bossIDList;
	}
	
	@Override
	public void acceptVisitor(IGoalVisitor visitor){
		System.out.println("acceptpointsvisitor");
		setIsFinished(visitor.visit(this));
	}

}
