package authoringEnvironment.authoringToolbar;

import authoringEnvironment.LevelModel;
import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.mainWindow.GameMakerWindow;
import exampledata.XStreamHandlers.FXConverters;
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
public class SaveGameMenu {

    private Menu myFileMenu;
    private MenuItem myNewFile;
    private List<LevelModel> myLevelModelList;


    public SaveGameMenu(){
        myFileMenu = new Menu("Save Game");
        myLevelModelList = new ArrayList<LevelModel>();
    }

    public void saveMyGame(GameMakerWindow myWindow) {
        TabPane myLevels = myWindow.getMyTabPane();
        Map<ViewSprite, Model> myModelsMap = myWindow.getMap();

        for(Tab aTab: myLevels.getTabs()){
            Map<ViewSprite,Model> myLevelMap = new HashMap<ViewSprite,Model>();
            AnchorPane myCurrLevel = (AnchorPane) aTab.getContent();
            for(Node myView: myCurrLevel.getChildren()){
                ViewSprite myChar = (ViewSprite) myView;
                myLevelMap.put(myChar,myModelsMap.get(myChar));
            }
            myLevelModelList.add(new LevelModel(myLevelMap));
        }
        XStream xstream = new XStream(new StaxDriver());
        FXConverters.configure(xstream);

        //Object to XML Conversion
        String xml = xstream.toXML(myLevelModelList);
        
        FileWriter fw;
		try {
			fw = new FileWriter(System.getProperty("user.dir") + "/SavedGameData/DefaultGames/my-file.xml");
			fw.write(xml);
		    fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		ArrayList<LevelModel> test = (ArrayList<LevelModel>) xstream.fromXML(xml);
//		for(ViewSprite v : test.get(0).getMyMap().keySet()) {
//			System.out.println(v.getMyImage());
//		}
        //System.out.println(xml);

    }

    public void addLevelTab(GameMakerWindow myWindow){
        myNewFile = new MenuItem("Save Current Game");
        myNewFile.setOnAction(e->saveMyGame(myWindow));
        myFileMenu.getItems().add(myNewFile);

    }

    public List<LevelModel> getMyLevelModelList(){
        return myLevelModelList;
    }


    public Menu getMenu(){
        return myFileMenu;
    }


}
