// This entire file is part of my masterpiece.
// David Yan
// Important Note: I renamed this class from AClickableWindow to AClickableScreen.
//
// I have added a brief summary of the class below, but I go into more detail in the Analysis
// markdown file.
// The main thought behind my masterpiece is creating a very flexible abstract window, or screen, class that allows users
// to make display screens from this abstract screen. Within the screens made from this abstract class, users can add Nodes
// to the screen, and any Nodes that are added to the screen immediately have drag and drop functionality implemented.
// Additionally, this abstract class allows users to set custom methods for when node is clicked, dragged, and even
// when nodes are right clicked on the screen to fit their specific project. In that way, users can customize exactly how
// drag and drop functionality should work in their projects, in addition to allowing for users to define what happens
// when nodes on the screen are clicked or dragged. (This will be shown in the implementation example).
//
// Originally, this class was hard-coded to only accept our custom ViewSprite objects, and the events for clicks and
// drags were tailored only toward our project. While they worked well for our project’s purposes, gave no power
// to anyone else using the class to define custom methods for what happens during these click/drag events for themselves.
//
// When I began refactoring, I first made sure that users were able to add very flexible Node objects to the screen, thus
// allowing flexibility for more general types of objects to be able to be displayed within the screen.
// Overall, this class gives tremendous flexibility to the user and alleviates the headache of setting up drag and
// drop/click events for Nodes on a screen. With this class, the user can add nodes to the screen using the
// addWithClicking() method, then fill in three methods that tell the screen and nodes what to do when a
// node is clicked, dragged, or right-clicked.
// The design also follows the Open/Closed principle, where the class is fully open to extension but closed for modification,
// and this code follows that principle in that a user can extend this class to override the click/drag methods to create
// their own custom methods or events for clicking/dragging, but the user can extend the functionality provided by this
// class with no need to make any changes to any part of the existing project besides creating a new class.


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

public abstract class AClickableScreen implements ClipboardOwner{
	private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private Node currentNode;
    private Pane myNewGamePane;

    public AClickableScreen() {
        myNewGamePane = new AnchorPane();
        setBackground(FrontEndData.DEFAULT_BACKGROUND);
	}

    public abstract void clickEvent(Node mySource, double x, double y);

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

    public abstract void dragEvent(Node mySource);

    private EventHandler<MouseEvent> nodeOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent t) {
            Node mySprite = ((Node) (t.getSource()));
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            dragEvent(mySprite);
            t.consume();
        }
    };
    public abstract void rightClickEvent(Node currNode, double x, double y);

    public void makeRightClickEvent(MouseEvent t) {
        if (t.getButton() == MouseButton.SECONDARY) {
            Node currSprite = (Node) t.getSource();
            double xPos = t.getSceneX();
            double yPos = t.getSceneY();
            rightClickEvent(currSprite, xPos, yPos);
        }
        t.consume();
    }

    public void setClicking(Node sprite) {
		sprite.setCursor(Cursor.HAND);
		sprite.setOnMousePressed(nodeOnMousePressedEventHandler);
		sprite.setOnMouseDragged(nodeOnMouseDraggedEventHandler);
		sprite.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
           makeRightClickEvent(e);
        });
	}

    public void addWithClicking(Node sprite) {
        setClicking(sprite);
        myNewGamePane.getChildren().add(sprite);
    }

    public void setBackground(String background) {
        if (background.equals(FrontEndData.DEFAULT_BACKGROUND)) {
            myNewGamePane.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), null, null)));
        } else {
            myNewGamePane.setBackground(new Background(new BackgroundImage(new Image(background), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
    }
    public abstract void updateSettingsPane(Node clickedSprite);

    public void setCurrentNode(Node currentNode) {this.currentNode = currentNode;}

    public void setMyNewGamePane(Pane myNewGamePane) {this.myNewGamePane = myNewGamePane;}

    public Pane getMyNewGamePane() {return myNewGamePane;}

    public void setOrgTranslateX(double orgTranslateX) {this.orgTranslateX = orgTranslateX;}

    public void setOrgTranslateY(double orgTranslateY) {this.orgTranslateY = orgTranslateY;}
}
