package authoringEnvironment.itemWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ItemWindowData {
	public static String TAB = "Tab "; 
	public static String VIEWSPRITE = "authoringEnvironment.ViewSprite";

	public static String SPRITEIMAGES = "resources/SpriteImages";
	public static ResourceBundle SpriteImages = ResourceBundle.getBundle(SPRITEIMAGES);

	public static String ITEMPATHS = "resources/ItemPaths";
	public static ResourceBundle ItemPaths = ResourceBundle.getBundle(ITEMPATHS);

	public static ArrayList<String> TabTypes = new ArrayList<>(Arrays.asList("Player", "Enemy", "Background"));
	//

}
