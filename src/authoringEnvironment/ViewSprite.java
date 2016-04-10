package authoringEnvironment;

/**
 * Created by davidyan on 4/4/16.
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewSprite extends ImageView {

    private String myRef;
//    private List<NumProperty> myPropertiesList;
//    private Health myHealth;
//    private Attack myAttack;
//    private Defense myDefense;

    public ViewSprite() {
        super();
//        myHealth = new Health();
//        myAttack = new Attack();
//        myDefense = new Defense();
//        myPropertiesList = new ArrayList<>();
//        myPropertiesList.add(myHealth);
//        myPropertiesList.add(myAttack);
//        myPropertiesList.add(myDefense);
    }
    
    public ViewSprite(String imagePath) {
    	super();
    	setImage(imagePath);
    }


    public void setImage(String imagePath){
        myRef = imagePath;
        Image image = new Image(imagePath);
        setImage(image);
    }

    public String getMyImage(){
        return myRef;
    }

//    public ImageView getImageView(){
//        return imageview;
//    }

//    public List<NumProperty> getMyProperties(){
//        return myPropertiesList;
//    }



}