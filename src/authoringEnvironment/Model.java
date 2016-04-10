package authoringEnvironment;

import spriteProperties.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidyan on 4/7/16.
 */
public class Model {
    private String myRef;
    private List<NumProperty> myPropertiesList;
    private Health myHealth;
    private Attack myAttack;
    private Defense myDefense;
    private List<WeaponProperty> myWeaponsList = new ArrayList<>();
    private WeaponProperty myWeapon;

    public Model(String ref) {
        myHealth = new Health();
        myAttack = new Attack();
        myDefense = new Defense();
        myPropertiesList = new ArrayList<>();
        myPropertiesList.add(myHealth);
        myPropertiesList.add(myAttack);
        myPropertiesList.add(myDefense);
        myWeapon = new MachineGun();
        myWeaponsList.add(new Pistol());
        myWeaponsList.add(new Bazooka());
        myWeaponsList.add(new MachineGun());
        myRef = ref;
    }


    public List<NumProperty> getMyPropertiesList() {
        return myPropertiesList;
    }
    public List<WeaponProperty> getMyWeaponsList() {
        return myWeaponsList;
    }


    public void setMyPropertiesList(List<NumProperty> myPropertiesList) {
        this.myPropertiesList = myPropertiesList;
    }

    public Health getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(Health myHealth) {
        this.myHealth = myHealth;
    }

    public Attack getMyAttack() {
        return myAttack;
    }

    public void setMyAttack(Attack myAttack) {
        this.myAttack = myAttack;
    }

    public Defense getMyDefense() {
        return myDefense;
    }

    public void setMyDefense(Defense myDefense) {
        this.myDefense = myDefense;
    }


    public String getMyRef() {
        return myRef;
    }

    public void setMyRef(String myRef) {
        this.myRef = myRef;
    }

    public WeaponProperty getMyWeapon() {
        return myWeapon;
    }

    public void setMyWeapon(WeaponProperty myWeapon) {
        this.myWeapon = myWeapon;
    }




}
