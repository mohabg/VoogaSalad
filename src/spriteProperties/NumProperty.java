package spriteProperties;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by davidyan on 4/6/16.
 */

public abstract class NumProperty implements IProperties {
    private double myValue;
    private DoubleProperty val = new SimpleDoubleProperty();
    public NumProperty(){
        myValue = 100;
        val.set(100);
    }

    public DoubleProperty getDouble() {
        return val;
    }

    public void setDouble(double value) {
        val.set(value);
    }

    public NumProperty(double num){
        myValue = num;
    }

    public double getMyValue(){
        return myValue;
    }

    public void setMyValue(double val){myValue=val;}

    public abstract String toString();

}
