package authoringEnvironment;

import gameElements.SpriteProperties;
import gameElements.ISpriteProperties;
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
	private ISpriteProperties mySpriteProperties;
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
		double imageWidth = imageProp.get().getWidth();
		double imageHeight = imageProp.get().getHeight();
		this.mySpriteProperties.setWidth(imageWidth);
		this.mySpriteProperties.setHeight(imageHeight);
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
	
	public ISpriteProperties getMySpriteProperties() {
		return mySpriteProperties;
	}


	public void setImage(String imagePath) {
		myRef.set(imagePath);
		Image image = new Image(imagePath);
		setImage(image);
	}


	public String getMyImage() {
		return myRef.getValue();
	}

	public double getHeight() {
		return getImage().getHeight();
	}

	public double getWidth() {
		return getImage().getWidth();

	}

	public void setMySpriteProperties(ISpriteProperties sp) {
		mySpriteProperties = sp;
	}

	public void bindToSprite(Sprite s){
		ISpriteProperties properties = s.getSpriteProperties();
		setMySpriteProperties(properties);
		xProperty().bindBidirectional(properties.getXProperty());
		yProperty().bindBidirectional(properties.getYProperty());
		fitHeightProperty().bindBidirectional(properties.getHeightProperty());
		fitWidthProperty().bindBidirectional(properties.getWidthProperty());
		rotateProperty().bindBidirectional(properties.getAngleProperty());
	}


}