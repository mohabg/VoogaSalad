package gameElements;

import level.LevelProperties;
import voogasalad.util.hud.source.HUDController;
import voogasalad.util.hud.source.IValueFinder;
import voogasalad.util.hud.source.Property;

public class HUDValueFinder implements IValueFinder{

	private HUDController controller;
	public LevelProperties levelProperties;
	
	public HUDValueFinder(){
		
	}
	
	public void setLevelProperties(LevelProperties levelProperties){
		this.levelProperties = levelProperties;
	}
	
	@Override
	public Property find(String key) {
		Property ret = null;
		switch(key.toLowerCase()){
		case "Health":
			ret = levelProperties.getUserControlledSprite().getMyHealth().getHealthProperty();
		}
		
		ret.addObserver(controller);
		return ret;
	}

	@Override
	public void setController(HUDController controller) {
		this.controller = controller;
	}

	@Override
	public void setDataSource(Object dataSource) {
		// TODO Auto-generated method stub
		
	}


	
}
