package events;

import behaviors.IActions;

public interface Executable {
	
	public void execute(IActions action);

	public void stop(IActions actions);

}
