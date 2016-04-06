package spriteProperties;

/**
 * Created by davidyan on 4/6/16.
 */
public class Health {
    private int myHealth;
    public Health(){
        myHealth = 100;
    }
    public Health(int useHealth){
        myHealth = useHealth;
    }
    public int getHealth(){
        return myHealth;
    }
    public void setHealth(int useHealth){
        myHealth = useHealth;
    }
}
