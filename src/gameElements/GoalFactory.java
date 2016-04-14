package gameElements;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Uses reflection to create a goal with information from the GoalProperties class.  
 */


import gameElements.Goal.Goals;


public class GoalFactory {
	private String path = "";
	
	
	
	/**
	 * @param myProperties GoalProperties object that you feed in to this class
	 * @param Goal The goal that will be made from the different goal properties that were fed in
	 */
	public Goal makeGoal(GoalProperties myProperties){
		Constructor<?> constructor = null;
		Goal goal=null;
		String goalType = myProperties.getMyGoal().toString();
		try{
			Class<?> goalClass= Class.forName(path + goalType);
			try{
				constructor=goalClass.getConstructor(myProperties.getClass());
			} catch(NoSuchMethodException exception){
				exception.printStackTrace();
			}
			try{
				goal= (Goal) constructor.newInstance(myProperties);
			} catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception){
				exception.printStackTrace();
			}
		} catch (ClassNotFoundException exception){
			exception.printStackTrace();
		}
		if(goal==null){
			GoalProperties property=new GoalProperties(Goals.PointsGoal);
			goal=new PointsGoal(property);
		}
		return goal;
	}

}
