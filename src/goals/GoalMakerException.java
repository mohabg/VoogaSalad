package goals;

/**
 * Created by davidyan on 4/30/16.
 */
public class GoalMakerException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Your Goal could not be made. Please check the exact conditions for making a goal.";

    public GoalMakerException(){
        super(ERROR_MESSAGE);
    }

    public GoalMakerException(String message){
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
