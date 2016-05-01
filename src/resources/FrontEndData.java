package resources;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
/**
 * @author Huijia Yu
 */
public class FrontEndData {
	public static String APPLICATION_CSS = "application/application.css";
	
	public static String TAB = "Level ";
	public static String VIEWSPRITE = "authoringEnvironment.ViewSprite";

	public static String SPRITEIMAGES = "resources/SpriteImages";
	public static ResourceBundle SpriteImages = ResourceBundle.getBundle(SPRITEIMAGES);

	public static String ITEMPATHS = "resources/ItemPaths";
	public static ResourceBundle ItemPaths = ResourceBundle.getBundle(ITEMPATHS);

	public static ArrayList<String> TabTypes = new ArrayList<>(Arrays.asList("Player", "Enemy", "Background", "Obstacles"));
	public static String TAB_TYPE_1 = "Player";
	public static String TAB_TYPE_2 = "Enemy";
	public static String  TAB_TYPE_3 = "Background";
	public static String  TAB_TYPE_4 =  "Obstacles";
	

	public static String BUTTONLABELS = "resources/buttonlabels";
    public static String STYLEPROPERTIES = "resources/styleProperties";
	public static ResourceBundle ButtonLabels = ResourceBundle.getBundle(BUTTONLABELS);
    public static ResourceBundle StyleLabels = ResourceBundle.getBundle(STYLEPROPERTIES);


    public static String STYLESHEET = "authoringEnvironment/itemWindow/styles.css";
    public static String TAB_STYLESHEET = "authoringEnvironment/authoringToolbar/authoringToolbar.css";
    public static String ITEMWINDOW_STYLESHEET = "authoringEnvironment/itemWindow/itemWindow.css";
    public static String MAINWINDOW_STYLESHEET = "authoringEnvironment/mainWindow/mainWindow.css";
    public static String STARTING_STYLESHEET = "authoringEnvironment/startOptionsWindow.css";
	public static String TILEPANE = "TilePane";
	

	public static File DEFAULT_DIRECTORY = new File(System.getProperty("user.dir") + "/SavedGameData/SavedGames");
	public static File SAVED_DIRECTORY = new File(System.getProperty("user.dir") + "/SavedGameData/SavedGames");

	public static String FILE_TYPE = ".xml";
	
	public static String DEFAULT_IMAGE = "pictures/gaming.png";

	public final static double VBOX_SPACING = 8;

    public final static double BUTTON_SIZE = 200;

}
