package spriteProperties;

/**
 * Created by davidyan on 4/6/16.
 */
public class Attack {
    private int myAttack;

    public Attack(){
        myAttack = 100;
    }
    public Attack(int use){
        myAttack = use;
    }

    public int getMyAttack() {
        return myAttack;
    }

    public void setMyAttack(int myAttack) {
        this.myAttack = myAttack;
    }
    public String toString(){
        return "Attack";
    }
}
