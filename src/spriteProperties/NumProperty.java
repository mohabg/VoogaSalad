package spriteProperties;

/**
 * Created by davidyan on 4/6/16.
 */

public abstract class NumProperty implements IProperties {
    private double myValue;

    public NumProperty(){
        myValue = 100;
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
