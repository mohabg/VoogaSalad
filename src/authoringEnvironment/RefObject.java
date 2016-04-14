package authoringEnvironment;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by davidyan on 4/12/16.
 */
public class RefObject {
    private StringProperty myRef;
    public RefObject(String ref){
    	myRef = new SimpleStringProperty(ref);
    }
    public String getMyRef(){
        return myRef.getValue();
    }
    public void setMyRef(String ref){
        myRef.set(ref);
    }
}
