package spriteProperties;

/**
 * Created by davidyan on 4/9/16.
 */
public class Bazooka extends WeaponProperty {
    public Bazooka(){
        super("Fast Gun");
    }

    public Bazooka(String myWeapon){
        super(myWeapon);
    }

    public String toString(){
        return "Fast Gun";
    }
}
