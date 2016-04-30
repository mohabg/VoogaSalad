package authoringEnvironment;

import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author davidyan, Huijia Yu, Joe Jacob
 * Factory to create copies of ViewSprites and set up the binded properties
 * Create the map that connects the Model Sprite to its corresponding ViewSprite
 *
 */
public class AESpriteFactory {
	
	public AESpriteFactory(){
		
	}
	public Sprite makeSprite(ViewSprite vs) {
		ISpriteProperties sp = vs.getMySpriteProperties();
		Sprite s = new Sprite();
		s.setMySpriteProperties(sp);
		s.setMyRef(new RefObject(vs.getMyImage()));
		vs.bindToSprite(s);
		return s;
	}
	public ViewSprite makeViewSprite(Sprite s) {
		ISpriteProperties sp = s.getSpriteProperties();
		ViewSprite vs = new ViewSprite(s.getMyRef());
		vs.bindToSprite(s);
		
		return vs;

	}
	public ViewSprite clone(ViewSprite vs){
		return new ViewSprite(vs.getMyImage()); 
	}
	
	public Map<ViewSprite, Sprite> makeMap(List<Sprite> list){
		Map<ViewSprite, Sprite> map = new HashMap<ViewSprite, Sprite>();
		list.forEach(s -> map.put(makeViewSprite(s), s));
		return map;
	}

}
