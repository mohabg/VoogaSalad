package spriteProperties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by davidyan on 4/9/16.
 */
public abstract class WeaponProperty extends Behavior {
    private StringProperty myWeapon = new SimpleStringProperty();
    private IntegerProperty myNumBullets = new SimpleIntegerProperty();

    public WeaponProperty(String val){
        myWeapon.set(val);
        myNumBullets.set(10);
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


}
