package gameplayer;

/**
 * Created by davidyan on 4/30/16.
 */
public class SaveGameException extends RuntimeException {

    private static final String ERROR_MESSAGE = "The game file you chose to save was unable to be saved. Please check the format of your XML output to be sure it contains all the fields needed to save.";

    public SaveGameException(){
        super(ERROR_MESSAGE);
    }

    public SaveGameException(String message){
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
