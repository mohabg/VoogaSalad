package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import resources.FrontEndData;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

	public class Main extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
				BorderPane root = new BorderPane();
				Scene scene = new Scene(root,400,400);
				scene.getStylesheets().add(getClass().getResource(FrontEndData.APPLICATION_CSS).toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}
	}
