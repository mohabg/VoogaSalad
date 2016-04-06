package spriteProperties;

/**
 * Created by davidyan on 4/6/16.
 */
public class Defense {
    private int myDefense;

    public Defense(){
        myDefense = 100;
    }

    public Defense(int useHealth){
        myDefense = useHealth;
    }

    public int getMyDefense(){
        return myDefense;
    }

    public void setMyDefense(int useDef){
        myDefense = useDef;
    }

    public String toString(){
        return "Defense";
    }

}
