package events;

import behaviors.IActions;
import level.LevelProperties;

public interface Executable {
	
	public void execute(IActions action, LevelProperties levProps);

}
