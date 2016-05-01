package goals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Uses reflection to create a goal with information from the GoalProperties class.
 */


public class GoalFactory {
	private String path = "goals.";
	

	
	/**
	 * @param myProperties GoalProperties object that you feed in to this class
	 */
	public Goal makeGoal(GoalProperties myProperties){
		Constructor<?> constructor = null;
		Goal goal=null;
		String goalType = myProperties.getGoalName().get();
		System.out.println("goalType" + goalType);
		try{
			System.out.println(path+goalType);
			Class<?> goalClass= Class.forName(path+goalType);
			try{
				constructor=goalClass.getConstructor(myProperties.getClass());
			} catch(NoSuchMethodException exception){
                }
			try{
				goal= (Goal) constructor.newInstance(myProperties);
			} catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception){
                }
		} catch (ClassNotFoundException exception){
            }
		System.out.println("prename"+goal.getGoal().name());
		if(goal==null){
			GoalProperties property=new GoalProperties(Goals.StayAliveGoal);
			goal=new StayAliveGoal(property);
			}
		System.out.println("postname"+goal.getGoal().name());

		return goal;
	}
}
