package itemWindow;

/**
 * Created by davidyan on 4/4/16.
 */

import authoringEnvironment.ViewSprite;
import javafx.scene.control.Tab;
import java.util.ArrayList;

public interface ITab {

    public void populateTab(ArrayList<ViewSprite> viewSprites);
    public void setTabTitle(String tabTitle);
    public Tab getTab();

}