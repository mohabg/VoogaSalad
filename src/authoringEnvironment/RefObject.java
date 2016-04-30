package authoringEnvironment;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by davidyan on 4/12/16.
 * Simple object containing the string image path for each sprite in the screen
 */
public class RefObject {
    private StringProperty myRef;
    
    public RefObject() {
    	this("");
    }
    
    public RefObject(String ref){
    	myRef = new SimpleStringProperty(ref);
    }
    public String getMyRef(){
        return myRef.getValue();
    }
    public void setMyRef(String ref){
        myRef.set(ref);
    }
    public StringProperty getMyStringRef() {
    	return myRef;
    }
}
