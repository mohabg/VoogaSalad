package authoringEnvironment;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by davidyan on 4/11/16.
 */
public class ExampleAttack {

    private IntegerProperty myAttack;
    private DoubleProperty myProbability;
    private BooleanProperty myBoolean;
    private StringProperty myStringProp;

    public ExampleAttack(){

    }
    public ExampleAttack(int attack, double prob, boolean myBool, String myString){
        myAttack.set(attack) ;
        myProbability.set(prob);
        myBoolean.set(myBool);
        myStringProp.set(myString);
    }
}
