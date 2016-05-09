package goals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//This entire file is part of my masterpiece.
//Srikar Pyda
//The Goal Factory class utilizes reflection in order to initialize a goal from the GoalProperties class. If the GoalProperties object is null, the Goal Factory automatically returns a default StayAlive goal. The utilization of the states design pattern allowed for a simplification of the logic required in utilizing reflection: the utilization of a goal-State implemented by the Goal Enumeration avoids long, repetitive conditional statements which are not modular. This design pattern allows for the architecture of Goal design to integrate extensions as opposed to having to start from a new framework in order to allow for new functionality. The factory design pattern enables a common interface to instantiate objects while masking their creation logic.
//The States Design Pattern facilitates the creation of a GoalFactory object, which implements the Factory design pattern in creating a centralized interface to create goals without exposing the creation-logic to the public.
// This class is part of my masterpiece because it demonstrates the usefulness of implementing the states design pattern in order to avoid dependencies and hard-coding. 
// Furthermore, it demonstrates the utility of combining the State Design Pattern with the Factory design pattern.

public class GoalFactory {
	private String path = "goals.";



	/**
	 * @param myProperties GoalProperties object that you feed in to this class
	 */
	public IGoal makeGoal(GoalProperties myProperties){
		Constructor<?> constructor = null;
		IGoal goal=null;
		String goalType = myProperties.getGoalName().get();
	//	System.out.println("goalType" + goalType);
		try{
			
	//		System.out.println(path+goalType);
			Class<?> goalClass= Class.forName(path+goalType);
			
			try{
				constructor=goalClass.getConstructor(myProperties.getClass());
				
			} catch(NoSuchMethodException exception){
				
			}
			
			try{
				
				goal= (IGoal) constructor.newInstance(myProperties);
			} catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception){
				
				
			}
		} catch (ClassNotFoundException exception){
			
		}
		
		if(goal==null){
			GoalProperties property=new GoalProperties(GoalEnum.StayAliveGoal);
			goal=new StayAliveGoal(property);
		}

		return goal;
	}
}
