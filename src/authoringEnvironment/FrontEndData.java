package authoringEnvironment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
/**
 * @author Huijia Yu
 */
public class FrontEndData {
	public static String TAB = "Level ";
	public static String VIEWSPRITE = "authoringEnvironment.ViewSprite";

	public static String SPRITEIMAGES = "resources/SpriteImages";
	public static ResourceBundle SpriteImages = ResourceBundle.getBundle(SPRITEIMAGES);

	public static String ITEMPATHS = "resources/ItemPaths";
	public static ResourceBundle ItemPaths = ResourceBundle.getBundle(ITEMPATHS);

	public static ArrayList<String> TabTypes = new ArrayList<>(Arrays.asList("Player", "Enemy", "Background"));
	

	public static String BUTTONLABELS = "resources/buttonlabels";
	public static ResourceBundle ButtonLabels = ResourceBundle.getBundle(BUTTONLABELS);

	public static String STYLESHEET = "authoringEnvironment/itemWindow/TabStyles.css";
	public static String TILEPANE = "TilePane";
	

	public static File DEFAULT_DIRECTORY = new File(System.getProperty("user.dir") + "/SavedGameData/DefaultGames");
	public static File SAVED_DIRECTORY = new File(System.getProperty("user.dir") + "/SavedGameData/SavedGames");

	public static String FILE_TYPE = ".xml";
	
	public  static String DEFAULT_IMAGE = "pictures/gaming.png";
	public final static double VBOX_SPACING = 8;

}
