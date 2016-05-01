package authoringEnvironment.itemWindow.ImageResizing;

import javafx.scene.Node;

public interface IOnDragResizeEventListener {
    void onDrag(Node node, double x, double y, double h, double w);

    void onResize(Node node, double x, double y, double h, double w);
}