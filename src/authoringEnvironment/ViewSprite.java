package authoringEnvironment;

/**
 * Created by davidyan on 4/4/16.
 */
import spriteProperties.Health;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import settingsWindow.SettingsWindow;
import spriteProperties.Attack;
import spriteProperties.Defense;

public class ViewSprite {

    private ImageView imageview;
    private SettingsWindow mySettingsWindow;
    private String myRef;
    private Health myHealth;
    private Attack myAttack;
    private Defense myDefense;

    public ViewSprite(SettingsWindow myWindow) {
        imageview = new ImageView();
        myHealth = new Health(100);
        myAttack = new Attack();
        myDefense = new Defense();
        mySettingsWindow = myWindow;
    }


    public void setImage(String imagePath){
        myRef = imagePath;
        Image image = new Image(imagePath);
        this.imageview.setImage(image);

    }

    public void setSettingsContent(){
        mySettingsWindow.setContent(myHealth,myAttack,myDefense);
    }

    public String getImage(){
        return myRef;
    }

    public ImageView getImageView(){
        return imageview;
    }



}