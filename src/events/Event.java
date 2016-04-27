package events;

import behaviors.IActions;
import level.LevelProperties;

/**
 * 
 * @author gauravkumar
 *
 */
public abstract class Event {
	
	private static String DEFAULT_EVENT_NAME = "unnamedEvent";
	
	private String name;
	private Trigger trigger;
	private Executable executable;
	
	public Event() {
		name = DEFAULT_EVENT_NAME;
	}
	
	public Event(String name, Trigger trigger, Executable executable) {
		this.name = name;
		this.trigger = trigger;
		this.executable = executable;
	}
	
	public Executable getExecutable() {
		return executable;
	}

	public void setExecutable(Executable executable) {
		this.executable = executable;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Trigger getTrigger() {
		return trigger;
	}
	
	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
	
	public abstract void doEvent(IActions action, LevelProperties levProps);

}
