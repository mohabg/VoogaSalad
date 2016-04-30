package authoringEnvironment.itemWindow.ImageResizing;



import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 *  ************* How to use ************************
 *
 * Rectangle rectangle = new Rectangle(50, 50);
 * rectangle.setFill(Color.BLACK);
 * DragResizeMod.makeResizable(rectangle, null);
 *
 * Pane root = new Pane();
 * root.getChildren().add(rectangle);
 *
 * primaryStage.setScene(new Scene(root, 300, 275));
 * primaryStage.show();
 *
 * ************* OnDragResizeEventListener **********
 *
 * You need to override OnDragResizeEventListener and
 * 1) preform out of main field bounds check
 * 2) make changes to the node
 * (this class will not change anything in node coordinates)
 *
 * There is defaultListener and it works only with Canvas nad Rectangle
 */

public class DragResizer {
  
    private double clickX, clickY, nodeX, nodeY, nodeH, nodeW;
    private ResizeState state = ResizeState.DEFAULT;
    private Node node;
    private IOnDragResizeEventListener listener;

    private final int MARGIN = 10;
    private final double MIN_W = 30;
    private final double MIN_H = 20;

    public DragResizer(Node node, IOnDragResizeEventListener listener) {
        this.node = node;
        if (listener != null) {
            this.listener = listener;
        }
    }


    public void mouseReleased(MouseEvent event) {
        node.setCursor(Cursor.DEFAULT);
        state = ResizeState.DEFAULT;
    }

    public void mouseOver(MouseEvent event) {
        ResizeState state = currentMouseState(event);
        Cursor cursor = getCursorForState(state);
        node.setCursor(cursor);
    }

    private ResizeState currentMouseState(MouseEvent event) {
        ResizeState state = ResizeState.DEFAULT;
        boolean left = isLeftResizeZone(event);
        boolean right = isRightResizeZone(event);
        boolean top = isTopResizeZone(event);
        boolean bottom = isBottomResizeZone(event);

        if (left && top) state = ResizeState.NW_RESIZE;
        else if (left && bottom) state = ResizeState.SW_RESIZE;
        else if (right && top) state = ResizeState.NE_RESIZE;
        else if (right && bottom) state = ResizeState.SE_RESIZE;
        else if (right) state = ResizeState.E_RESIZE;
        else if (left) state = ResizeState.W_RESIZE;
        else if (top) state = ResizeState.N_RESIZE;
        else if (bottom) state = ResizeState.S_RESIZE;
        else if (isInDragZone(event)) state = ResizeState.DRAG;

        return state;
    }

    private static Cursor getCursorForState(ResizeState state) {
        switch (state) {
            case NW_RESIZE:
                return Cursor.NW_RESIZE;
            case SW_RESIZE:
                return Cursor.SW_RESIZE;
            case NE_RESIZE:
                return Cursor.NE_RESIZE;
            case SE_RESIZE:
                return Cursor.SE_RESIZE;
            case E_RESIZE:
                return Cursor.E_RESIZE;
            case W_RESIZE:
                return Cursor.W_RESIZE;
            case N_RESIZE:
                return Cursor.N_RESIZE;
            case S_RESIZE:
                return Cursor.S_RESIZE;
            default:
                return Cursor.DEFAULT;
        }
    }


    public void mouseDragged(MouseEvent event) {
        if (listener != null) {
            double mouseX = parentX(event.getX());
            double mouseY = parentY(event.getY());
            
            if (state == ResizeState.DRAG) {
                listener.onDrag(node, mouseX - clickX, mouseY - clickY, nodeH, nodeW);
            } else if (state != ResizeState.DEFAULT) {
                //resizing
                double newX = nodeX;
                double newY = nodeY;
                double newH = nodeH;
                double newW = nodeW;

                // Right Resize
                if (state == ResizeState.E_RESIZE || state == ResizeState.NE_RESIZE || state == ResizeState.SE_RESIZE) {
                    newW = mouseX - nodeX;
                }
                // Left Resize
                if (state == ResizeState.W_RESIZE || state == ResizeState.NW_RESIZE || state == ResizeState.SW_RESIZE) {
                    newX = mouseX;
                    newW = nodeW + nodeX - newX;
                }

                // Bottom Resize
                if (state == ResizeState.S_RESIZE || state == ResizeState.SE_RESIZE || state == ResizeState.SW_RESIZE) {
                    newH = mouseY - nodeY;
                }
                // Top Resize
                if (state == ResizeState.N_RESIZE || state == ResizeState.NW_RESIZE || state == ResizeState.NE_RESIZE) {
                    newY = mouseY;
                    newH = nodeH + nodeY - newY;
                }

                //min valid rect Size Check
                if (newW < MIN_W) {
                    if (state == ResizeState.W_RESIZE || state == ResizeState.NW_RESIZE || state == ResizeState.SW_RESIZE)
                        newX = newX - MIN_W + newW;
                    newW = MIN_W;
                }

                if (newH < MIN_H) {
                    if (state == ResizeState.N_RESIZE || state == ResizeState.NW_RESIZE || state == ResizeState.NE_RESIZE)
                        newY = newY + newH - MIN_H;
                    newH = MIN_H;
                }

                listener.onResize(node, newX, newY, newH, newW);
            }
        }
    }

    public void mousePressed(MouseEvent event) {

        if (isInResizeZone(event)) {
            setNewInitialEventCoordinates(event);
            state = currentMouseState(event);
        } else if (isInDragZone(event)) {
            setNewInitialEventCoordinates(event);
            state = ResizeState.DRAG;
        } else {
            state = ResizeState.DEFAULT;
        }
    }

    private void setNewInitialEventCoordinates(MouseEvent event) {
        nodeX = nodeX();
        nodeY = nodeY();
        nodeH = nodeH();
        nodeW = nodeW();
        clickX = event.getX();
        clickY = event.getY();
    }

    public boolean isInResizeZone(MouseEvent event) {
        return isLeftResizeZone(event) || isRightResizeZone(event)
                || isBottomResizeZone(event) || isTopResizeZone(event);
    }

    public boolean isInDragZone(MouseEvent event) {
        double xPos = parentX(event.getX());
        double yPos = parentY(event.getY());
        double nodeX = nodeX() + MARGIN;
        double nodeY = nodeY() + MARGIN;
        double nodeX0 = nodeX() + nodeW() - MARGIN;
        double nodeY0 = nodeY() + nodeH() - MARGIN;

        return (xPos > nodeX && xPos < nodeX0) && (yPos > nodeY && yPos < nodeY0);
    }

    private boolean isLeftResizeZone(MouseEvent event) {
        return intersect(0, event.getX());
    }

    private boolean isRightResizeZone(MouseEvent event) {
        return intersect(nodeW(), event.getX());
    }

    private boolean isTopResizeZone(MouseEvent event) {
        return intersect(0, event.getY());
    }

    private boolean isBottomResizeZone(MouseEvent event) {
        return intersect(nodeH(), event.getY());
    }

    private boolean intersect(double side, double point) {
        return side + MARGIN > point && side - MARGIN < point;
    }

    private double parentX(double localX) {
        return nodeX() + localX;
    }

    private double parentY(double localY) {
        return nodeY() + localY;
    }

    private double nodeX() {
        return node.getBoundsInParent().getMinX();
    }

    private double nodeY() {
        return node.getBoundsInParent().getMinY();
    }

    private double nodeW() {
        return node.getBoundsInParent().getWidth();
    }

    private double nodeH() {
        return node.getBoundsInParent().getHeight();
    }
}
