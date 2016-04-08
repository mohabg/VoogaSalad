package authoringEnvironment;

import spriteProperties.Attack;
import spriteProperties.Defense;
import spriteProperties.Health;
import spriteProperties.NumProperty;

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


    public Model() {
        myHealth = new Health();
        myAttack = new Attack();
        myDefense = new Defense();
        myPropertiesList = new ArrayList<>();
        myPropertiesList.add(myHealth);
        myPropertiesList.add(myAttack);
        myPropertiesList.add(myDefense);
    }

    public List<NumProperty> getMyPropertiesList() {
        return myPropertiesList;
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

    private Defense myDefense;

    public String getMyRef() {
        return myRef;
    }

    public void setMyRef(String myRef) {
        this.myRef = myRef;
    }


}
