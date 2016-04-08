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


    public void setImage(String imagePath){
        myRef = imagePath;
        Image image = new Image(imagePath);
        setImage(image);
    }

//    public void setMyHealth(double test){
//        myHealth.setMyValue(test);
//    }
//
//    public double getMyHealth(){
//        return myHealth.getMyValue();
//    }

//    public void setSettingsContent(SettingsWindow mySettingsWindow){
//        VBox myBox = new VBox(8);
//        for(NumProperty aProp: myPropertiesList){
//            HBox myTempBox = new HBox();
//            Label myLabel = new Label(aProp.toString());
//            Slider mySlider = new Slider(0,100,aProp.getMyValue());
////            mySlider.setMin(0);
////            mySlider.setMax(100);
//            mySlider.setShowTickMarks(true);
//            mySlider.setShowTickLabels(true);
//
//            mySlider.valueProperty().addListener(new ChangeListener<Number>() {
//                public void changed(ObservableValue<? extends Number> ov,
//                                    Number old_val, Number new_val) {
//                    aProp.setMyValue((double) new_val);
//                }
//            });
//            myTempBox.getChildren().addAll(myLabel, mySlider);
//            myBox.getChildren().add(myTempBox);
//        }
//
//        mySettingsWindow.setContent(myBox);
//    }

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