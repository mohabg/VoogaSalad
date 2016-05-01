package events;

import behaviors.IActions;
import level.ILevelProperties;
import level.LevelProperties;

public interface Executable {
	
	public void execute(IActions action, ILevelProperties levProps);

	public void stop(IActions actions, ILevelProperties levProps);

}
