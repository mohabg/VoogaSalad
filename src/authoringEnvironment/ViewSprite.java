package authoringEnvironment;

/**
 * Created by davidyan on 4/4/16.
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import settingsWindow.SettingsWindow;

public class ViewSprite {

    ImageView imageview;
    SettingsWindow mySettingsWindow;
    String myRef;

    public ViewSprite() {
        imageview = new ImageView();
        mySettingsWindow = new SettingsWindow();
    }


    public void setImage(String imagePath){
        myRef = imagePath;
        Image image = new Image(imagePath);
        this.imageview.setImage(image);
    }

    public String getImage(){
        return myRef;
    }

    public ImageView getImageView(){
        return imageview;
    }



}