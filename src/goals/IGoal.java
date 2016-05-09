package goals;

public interface IGoal {

	void acceptVisitor(IGoalVisitor visitor);
}
