package authoringEnvironment;

/**
 * Created by davidyan on 4/4/16.
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewSprite {

    ImageView imageview;

    public ViewSprite() {
        imageview = new ImageView();
    }

    public void setImage(String imagePath){
        Image image = new Image(imagePath);
        this.imageview.setImage(image);
    }

    public ImageView getImageView(){
        return imageview;
    }

}