package spriteProperties;

/**
 * Created by davidyan on 4/6/16.
 */
public abstract class NumProperty implements IProperties {
    private int myValue;

    public NumProperty(){
        myValue = 100;
    }

    public NumProperty(int num){
        myValue = num;
    }

    public int getMyValue(){
        return myValue;
    }

    public void setMyValue(int val){myValue=val;}

    public abstract String toString();

}
