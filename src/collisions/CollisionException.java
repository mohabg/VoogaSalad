package collisions;

/**
 * Created by davidyan on 4/30/16.
 */
public class CollisionException extends RuntimeException {

    private static final String ERROR_MESSAGE = "The collision you chose to implement does not exist or is not applicable to these two sprites.";

    public CollisionException(){
        super(ERROR_MESSAGE);
    }

    public CollisionException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return ERROR_MESSAGE;
    }

    public String getString(){
        return ERROR_MESSAGE;
    }

}
