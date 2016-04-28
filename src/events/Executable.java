package events;

import behaviors.IActions;
import level.LevelProperties;

public interface Executable {
	
	public void execute(IActions action, LevelProperties levProps);

	public void stop(IActions actions, LevelProperties levProps);

}
