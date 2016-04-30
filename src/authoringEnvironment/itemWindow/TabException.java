package authoringEnvironment.itemWindow;

/**
 * Created by davidyan on 4/30/16.
 */
public class TabException extends RuntimeException {

    private static final String MY_ERR0R_MESSAGE = "Tab of that format could not be made";

    public TabException(){
        super(MY_ERR0R_MESSAGE);
    }

    public TabException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return MY_ERR0R_MESSAGE;
    }

    public String getString(){
        return MY_ERR0R_MESSAGE;
    }
}
