package spriteProperties;

/**
 * Created by davidyan on 4/6/16.
 */
public class Health extends NumProperty {
//    private int myHealth;

    public Health(){
        super();
    }

    public Health(int useHealth){
        super(useHealth);
    }

//    public int getHealth(){
//        return myHealth;
//    }
//
//    public void setHealth(int useHealth){
//        myHealth = useHealth;
//    }

    public String toString(){
        return "Health";
    }
}
