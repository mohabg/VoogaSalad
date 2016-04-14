package authoringEnvironment;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewSprite extends ImageView {

	private StringProperty myRef;
	// private List<NumProperty> myPropertiesList;
	// private Health myHealth;
	// private Attack myAttack;
	// private Defense myDefense;
	private SpriteProperties mySpriteProperties;

	public ViewSprite() {
		super();
		myRef = new SimpleStringProperty();
		mySpriteProperties = new SpriteProperties();
		// myHealth = new Health();
		// myAttack = new Attack();
		// myDefense = new Defense();
		// myPropertiesList = new ArrayList<>();
		// myPropertiesList.add(myHealth);
		// myPropertiesList.add(myAttack);
		// myPropertiesList.add(myDefense);
	}

	public SpriteProperties getMySpriteProperties() {
		return mySpriteProperties;
	}

	public ViewSprite(String imagePath) {
		this();
		setImage(imagePath);
	}

	public void setImage(String imagePath) {
		myRef.set(imagePath);
		Image image = new Image(imagePath);
		setImage(image);
	}

	// public void setMyHealth(double test){
	// myHealth.setMyValue(test);
	// }
	//
	// public double getMyHealth(){
	// return myHealth.getMyValue();
	// }

	public String getMyImage() {
		return myRef.getValue();
	}

	public double getHeight() {
		// TODO Auto-generated method stub
		return getImage().getHeight();
	}

	public double getWidth() {
		// TODO Auto-generated method stub
		return getImage().getWidth();

	}

	// public ImageView getImageView(){
	// return imageview;
	// }

	// public List<NumProperty> getMyProperties(){
	// return myPropertiesList;
	// }

	// public ImageView getImageView(){
	// return imageview;
	// }

	// public List<NumProperty> getMyProperties(){
	// return myPropertiesList;
	// }

}