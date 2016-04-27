package authoringEnvironment;

import javafx.beans.property.*;

/**
 * @author David Yan
 */
public class ExampleAttack {

    private IntegerProperty myAttack;
    private DoubleProperty myProbability;
    private BooleanProperty myBoolean;
    private StringProperty myStringProp;

    public ExampleAttack(){
        myAttack = new SimpleIntegerProperty();
        myProbability = new SimpleDoubleProperty();
        myBoolean = new SimpleBooleanProperty();
        myStringProp = new SimpleStringProperty();

    }
    public ExampleAttack(int attack, double prob, boolean myBool, String myString){
        myAttack.set(attack) ;
        myProbability.set(prob);
        myBoolean.set(myBool);
        myStringProp.set(myString);
    }
}
