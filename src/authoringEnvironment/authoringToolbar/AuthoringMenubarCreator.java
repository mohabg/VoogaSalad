package authoringEnvironment.authoringToolbar;

import authoringEnvironment.MainAuthoringWindow;
import gameplayer.GameLoader;
import gameplayer.MainPlayingWindow;
import interfaces.ITabPane;
import resources.FrontEndData;
import voogasalad.util.hud.source.IAuthoringHUDController;
import voogasalad.util.hud.source.PopupSelector;

/**
 * This is the creator for the menubar, which allows the user to select new
 * files, add a level, save, or return to the start screen
 * 
 * @author Huijia, David Yan, Joe Jacob
 *
 */
public class AuthoringMenubarCreator extends AbstractMenuBar implements IAuthoringHUDController{

	// TODO SWITCH TO REFLECTION


	public AuthoringMenubarCreator(String gameName) {
		super(gameName);
	}

	/**
	 * This method initializes the menubar using the tabpane (to set the add tab
	 * action). We made this to eliminate dependencies.
	 * 
	 * @param mainAuthoringWindow
	 * 
	 * @param window
	 */
	public void initMenuBar(MainAuthoringWindow mainAuthoringWindow, ITabPane window) {
		MenuBarElement myBackMenu = getBackMenu(mainAuthoringWindow);
		MenuBarElement myFileMenuMaker = getFileMenu(window);
		MenuBarElement myGameMenu = getGameMenu(window);
		MenuBarElement myPlayToggleMenu = getPlayToggle(mainAuthoringWindow, window);
		MenuBarElement hud = hud();
		myMenuBar.getStylesheets().add(FrontEndData.TAB_STYLESHEET);
		myMenuBar.getMenus().addAll(myBackMenu.getMenu(), myFileMenuMaker.getMenu(), myGameMenu.getMenu(), myPlayToggleMenu.getMenu(), hud.getMenu());
	}
	
    private MenuBarElement getFileMenu(ITabPane window) {
    	MenuBarElement myFileMenuMaker = new MenuBarElement();
        myFileMenuMaker.setName(FrontEndData.ButtonLabels.getString("FileMenu"));
        myFileMenuMaker.setNewAction(FrontEndData.ButtonLabels.getString("FileMenu1"), e -> {
            System.out.println("NOT IMPLEMENTED");
        });
//        myFileMenuMaker.setNewAction(FrontEndData.ButtonLabels.getString("SaveGameMenu1"), e -> {
//            saveMyGame(window);
//        });
        
        return myFileMenuMaker;
    }
    
    private MenuBarElement getGameMenu(ITabPane window){
    	MenuBarElement myGameMenu = new MenuBarElement();
    	myGameMenu.setName(FrontEndData.ButtonLabels.getString("GameMenu"));
//    	myGameMenu.setNewAction(FrontEndData.ButtonLabels.getString("PlayMenu1"), e -> {
//             playMyGame(window);
//        });
    	myGameMenu.setNewAction(FrontEndData.ButtonLabels.getString("AddNewLevelMenu1"), e -> {
            window.addNewTab();
        });
    	return myGameMenu;
    }
    
    private MenuBarElement getPlayToggle(MainAuthoringWindow mainAuthoringWindow, ITabPane tabLevels){
    	MenuBarElement playToggleButton = new MenuBarElement();
    	playToggleButton.setName(FrontEndData.ButtonLabels.getString("Play/Edit"));
    	playToggleButton.setNewAction(FrontEndData.ButtonLabels.getString("PlayOption"), e -> {
    		GameLoader.saveGame(myName, tabLevels);

    		MainPlayingWindow myMainPlayingWindow = new MainPlayingWindow(mainAuthoringWindow, myName);
    		mainAuthoringWindow.switchScene(myMainPlayingWindow);
    	});
    	return playToggleButton;
    }
    
    private MenuBarElement hud(){
    	MenuBarElement hud = new MenuBarElement();
    	hud.setName("hud");
    	hud.setNewAction("hud", e-> {
    		new PopupSelector(this);
    	});
    	return hud;
    }

	@Override
	public void setHUDInfoFile(String location) {
		// TODO Auto-generated method stub
		
	}
    
//	private void playMyGame(ITabPane tabLevels) {
//		GameLoader.saveGame(myName, tabLevels);
//		Stage myStage = (Stage) myMenuBar.getScene().getWindow();
//		myStage.setScene(new PlayScreen(myName).getScreen().getScene());
//		myStage.centerOnScreen();
//	}

	

}