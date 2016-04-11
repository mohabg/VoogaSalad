package authoringEnvironment.authoringToolbar;

import authoringEnvironment.LevelModel;
import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.mainWindow.GameMakerWindow;
import authoringEnvironment.mainWindow.GameAuthoringTab;
import exampledata.XStreamHandlers.FXConverters;
import gameplayer.GameLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;



/**
 * Created by davidyan on 4/7/16.
 */
public class SaveGameMenu extends AbstractMenu{
    public SaveGameMenu(String menuName) {
    	super(menuName);
    }
}
