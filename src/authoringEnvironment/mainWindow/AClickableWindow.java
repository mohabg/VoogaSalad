package authoringEnvironment.mainWindow;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import resources.FrontEndData;

import java.awt.datatransfer.ClipboardOwner;

/**
 * @author davidyan, Huijia Yu
 * Abstract class that contains all methods needed to handle drag and drop and click events of Screens 
 * Includes the Authoring Environment Screen and the Game Player Screen
 */
public abstract class AClickableWindow implements ClipboardOwner{
	private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private Node currentNode;
    private Pane myNewGamePane;

    public AClickableWindow() {
        myNewGamePane = new AnchorPane();
        setBackground(FrontEndData.DEFAULT_BACKGROUND);
	}

	private EventHandler<MouseEvent> nodeOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;
			Node dragSource = (Node) t.getSource();
            clickEvent(dragSource, newTranslateX, newTranslateY);
			t.consume();
		}
	};

    public abstract void clickEvent(Node mySource, double x, double y);

	private EventHandler<MouseEvent> nodeOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent t) {
			Node mySprite = ((Node) (t.getSource()));
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            dragEvent(mySprite);
			t.consume();
		}
	};

    public abstract void dragEvent(Node mySource);

	public abstract void updateSettingsPane(Node clickedSprite);

	public void addWithClicking(Node sprite) {
		setClicking(sprite);
		myNewGamePane.getChildren().add(sprite);
	}

	public void setClicking(Node sprite) {
		sprite.setCursor(Cursor.HAND);
		sprite.setOnMousePressed(nodeOnMousePressedEventHandler);
		sprite.setOnMouseDragged(nodeOnMouseDraggedEventHandler);
		sprite.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
           makeRightClickEvent(sprite, e);
        });
	}

	public void setBackground(String background) {
		if (background.equals(FrontEndData.DEFAULT_BACKGROUND)) {
			myNewGamePane.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), null, null)));		
		} else {
			myNewGamePane.setBackground(new Background(new BackgroundImage(new Image(background), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		}
	}

    public void makeRightClickEvent(Node mySprite, MouseEvent t) {
        if (t.getButton() == MouseButton.SECONDARY) {
            Node currSprite = (Node) t.getSource();
            double xPos = t.getSceneX();
            double yPos = t.getSceneY();
            rightClickEvent(currSprite, xPos, yPos);
        }
        t.consume();
    }
    public abstract void rightClickEvent(Node currNode, double x, double y);

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public void setMyNewGamePane(Pane myNewGamePane) {
        this.myNewGamePane = myNewGamePane;
    }

    public Pane getMyNewGamePane() {
        return myNewGamePane;
    }

    public void setOrgTranslateX(double orgTranslateX) {
        this.orgTranslateX = orgTranslateX;
    }

    public void setOrgTranslateY(double orgTranslateY) {
        this.orgTranslateY = orgTranslateY;
    }

}
