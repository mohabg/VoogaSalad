package authoringEnvironment;

/**
 * Created by davidyan on 4/4/16.
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewSprite extends ImageView {

	private String myImagePath;
	
    public ViewSprite() {
        this("");
    }
    
    public ViewSprite(String imagePath) {
    	super();
    	setImage(imagePath);
    }

	public void setImage(String imagePath) {
		myImagePath = imagePath;
		Image image = new Image(imagePath);
		setImage(image);
	}


    public String getMyImagePath(){
        return myImagePath;
    }
    
    

}