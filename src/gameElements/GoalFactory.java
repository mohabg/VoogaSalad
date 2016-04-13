package gameElements;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GoalFactory {
	private String path = "";
	
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
		
		return goal;
	}

}
