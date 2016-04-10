package spriteProperties;

import javafx.beans.property.*;

/**
 * Created by davidyan on 4/9/16.
 */
public abstract class WeaponProperty extends Behavior {
    private StringProperty myWeapon = new SimpleStringProperty();
    private IntegerProperty myNumBullets = new SimpleIntegerProperty();
    private BooleanProperty isChosen = new SimpleBooleanProperty();

    public WeaponProperty(String val){
        myWeapon.set(val);
        myNumBullets.set(10);
        isChosen.set(false);
    }

    public int getMyNumBullets() {
        return myNumBullets.get();
    }

    public IntegerProperty myNumBulletsProperty() {
        return myNumBullets;
    }

    public void setMyNumBullets(int myNumBullets) {
        this.myNumBullets.set(myNumBullets);
    }

    public StringProperty getMyWeapon() {
        return myWeapon;
    }

    public void setMyWeapon(String value) {
        myWeapon.set(value);
    }

    public BooleanProperty getChosen(){
        return isChosen;
    }

    public void setIsChosen(boolean chosen){
        isChosen.set(chosen);
    }


}
