package spriteProperties;

/**
 * Created by davidyan on 4/9/16.
 */
public class MachineGun extends WeaponProperty {
    public MachineGun(){
        super("Little Gun");
    }

    public MachineGun(String myWeapon){
        super(myWeapon);
    }

    public String toString(){
        return "Little Gun";
    }
}
