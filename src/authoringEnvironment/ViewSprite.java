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
import spriteProperties.NumProperty;
import java.util.*;

public class ViewSprite {

    private ImageView imageview;
    private String myRef;
    private List<NumProperty> myPropertiesList;
    private Health myHealth;
    private Attack myAttack;
    private Defense myDefense;

    public ViewSprite() {
        imageview = new ImageView();
        myHealth = new Health();
        myAttack = new Attack();
        myDefense = new Defense();
        myPropertiesList = new ArrayList<>();
        myPropertiesList.add(myHealth);
        myPropertiesList.add(myAttack);
        myPropertiesList.add(myDefense);
    }


    public void setImage(String imagePath){
        myRef = imagePath;
        Image image = new Image(imagePath);
        this.imageview.setImage(image);

    }

    public void setSettingsContent(SettingsWindow mySettingsWindow){
        mySettingsWindow.setContent(myPropertiesList);
    }

    public String getImage(){
        return myRef;
    }

    public ImageView getImageView(){
        return imageview;
    }



}