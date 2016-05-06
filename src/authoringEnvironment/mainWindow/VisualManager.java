package authoringEnvironment.mainWindow;

import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.StylesheetType;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Main.ObjectEditorController;
import gameElements.ISprite;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import resources.FrontEndData;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.Map;

public class VisualManager {
    private ObjectEditorController myOEC;

    public VisualManager() {
        myOEC = new ObjectEditorController(Arrays.asList(FrontEndData.PACKAGE_NAMES));
        for (StylesheetType type : StylesheetType.values()) {
            myOEC.addObjectStylesheet(type, FrontEndData.STYLESHEET);
        }

    }

    public VBox setSettingsContent(LevelModel myLevel) {
        //setCurrentNode(null);
        VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
        TabPane propertiesList = myOEC.makeObjectEditorTabPane(myLevel);
        myBox.getChildren().addAll(propertiesList);
        return myBox;
    }

    public VBox setSettingsContent(ISprite iSprite, Map<ISprite, TabPane> myMap) {
        VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
        TabPane propertiesPane = new TabPane();
        if (myMap.containsKey(iSprite)) {
            propertiesPane = myMap.get(iSprite);
        } else {
            propertiesPane = myOEC.makeObjectEditorTabPane(iSprite);
            myMap.put(iSprite, propertiesPane);
        }
        myBox.getChildren().add(propertiesPane);
        return myBox;
    }

    ContextMenu createContextMenu(ViewSprite vs) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem copy = new MenuItem(FrontEndData.COPYIMAGE);
        contextMenu.getItems().addAll(copy);
        copy.setOnAction(event -> {
            StringSelection stringSelection = new StringSelection(vs.getMyImage());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });
        contextMenu.setAutoHide(true);
        return contextMenu;
    }
}