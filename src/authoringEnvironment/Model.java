package authoringEnvironment;

import spriteProperties.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidyan on 4/7/16.
 */
public class Model {
    private String myImagePath;
    private List<Behavior> myPropertiesList;
    private Health myHealth;
    private Attack myAttack;
    private Defense myDefense;
    //private List<WeaponProperty> myWeaponsList = new ArrayList<>();
    private WeaponProperty myWeapon;

    public Model(String ref) {
        myHealth = new Health();
        myAttack = new Attack();
        myDefense = new Defense();
        myPropertiesList = new ArrayList<Behavior>();
        
        myPropertiesList.add(myHealth);
        myPropertiesList.add(myAttack);
        myPropertiesList.add(myDefense);
        myWeapon = new MachineGun();
        myPropertiesList.add(new Pistol());
        myPropertiesList.add(new Bazooka());
        myPropertiesList.add(new MachineGun());
        myImagePath = ref;
    }


    public List<Behavior> getMyPropertiesList() {
        return myPropertiesList;
    }


    public void setMyPropertiesList(List<Behavior> myPropertiesList) {
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


    public String getMyImagePath() {
        return myImagePath;
    }

    public void setMyRef(String myRef) {
        this.myImagePath = myRef;
    }

    public WeaponProperty getMyWeapon() {
        return myWeapon;
    }

    public void setMyWeapon(WeaponProperty myWeapon) {
        this.myWeapon = myWeapon;
    }




}
