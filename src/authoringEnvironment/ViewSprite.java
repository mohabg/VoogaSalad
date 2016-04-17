package authoringEnvironment;

import gameElements.Sprite;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
	private ObjectProperty<Image> imageProp;
	
	public ViewSprite() {
		super();
		myRef = new SimpleStringProperty();
		mySpriteProperties = new SpriteProperties();
	}
	
	public ViewSprite(String imagePath) {
		this();
		myRef.set(imagePath);
		setImage(imagePath);
		imageProp = new SimpleObjectProperty<Image>(new Image(myRef.getValue()));
		this.imageProperty().bindBidirectional(imageProp);
	}
	
	public ViewSprite(String imagePath, SpriteProperties newProps) {
		this(imagePath);
		mySpriteProperties = newProps;
	}
	
	public ObjectProperty<Image> getMyImageProp(StringProperty sp) {
		return new SimpleObjectProperty<Image>(new Image(sp.getValue()));
	}
	
	public StringProperty stringRefProperty() {
		return myRef;
	}
	
	public SpriteProperties getMySpriteProperties() {
		return mySpriteProperties;
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

	public void setMySpriteProperties(SpriteProperties sp) {
		mySpriteProperties = sp;
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
	public void bindToSprite(Sprite s){
		setMySpriteProperties(s.getSpriteProperties());
		xProperty().bindBidirectional(s.getX());
		yProperty().bindBidirectional(s.getY());
		fitHeightProperty().bindBidirectional(s.getHeight());
		fitWidthProperty().bindBidirectional(s.getWidth());
		rotateProperty().bindBidirectional(s.getAngle());
	}

}