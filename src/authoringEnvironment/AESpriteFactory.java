package authoringEnvironment;

import java.util.List;
import java.util.Map;

import behaviors.Behavior;
import collisions.Collision;
import gameElements.Health;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import resources.FrontEndData;

public class AESpriteFactory {
	public AESpriteFactory(){
		
	}
	public Sprite makeSprite(ViewSprite vs) {
		SpriteProperties sp = vs.getMySpriteProperties();
		Sprite s = new Sprite();
		s.setMySpriteProperties(sp);
		s.setMyRef(new RefObject(vs.getMyImage()));
		vs.bindToSprite(s);
		
		return s;

	}
	public ViewSprite makeViewSprite(Sprite s) {
		SpriteProperties sp = s.getSpriteProperties();
		ViewSprite vs = new ViewSprite(s.getMyRef());
		vs.bindToSprite(s);
		
		return vs;

	}
	public ViewSprite clone(ViewSprite vs){
		return new ViewSprite(vs.getMyImage()); 
	}
//	
//	private ViewSprite makeViewSprite(String key) {
//		try {
//			Class c = Class.forName(FrontEndData.VIEWSPRITE);
//			ViewSprite viewsprite = (ViewSprite) c.newInstance();
//
//            String p = FrontEndData.SpriteImages.getString(key);
//            viewsprite.setImage(p);
//
//            Sprite newS = new Sprite(new RefObject(p));
//			viewsprite.setOnMouseClicked(e -> {
//				myGameTabPane.getCurrentTab().setTabContent(viewsprite, newS);
//			});
//
//			return viewsprite;
//		} catch (Exception e) {
//			e.printStackTrace();
//        }
//		return null;
//	}
}
