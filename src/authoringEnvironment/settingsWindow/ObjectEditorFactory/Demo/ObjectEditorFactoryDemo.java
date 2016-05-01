package authoringEnvironment.settingsWindow.ObjectEditorFactory.Demo;


import authoringEnvironment.settingsWindow.ObjectEditorFactory.Main.VisualFactory;
import gameElements.SpriteProperties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class ObjectEditorFactoryDemo extends Application {

	public static void main(String[] args) {		
		launch();		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VisualFactory myFactory = new VisualFactory();
		
		//Sprite myModel = new Sprite();
		//SpriteProperties myModel = new SpriteProperties();	
		SpriteProperties myModel = new SpriteProperties(1, 2, 3, 4, 5);
		myModel.getMyX().addListener((o, ov, nv) -> {
			System.out.println("i changed my value to " + nv.doubleValue());
		});
		
		TabPane modelSettings = myFactory.getMyTabs(myModel);
		
		Scene demoScene = new Scene(modelSettings, 800, 800);
		primaryStage.setScene(demoScene);
		primaryStage.show();
	}


}
