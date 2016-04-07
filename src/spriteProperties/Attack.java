package spriteProperties;

/**
 * Created by davidyan on 4/6/16.
 */
public class Attack extends NumProperty {
//    private int myAttack;

    public Attack(){
        super();
//        myAttack = 100;
    }
    public Attack(int use){
        super(use);
    }

//    public int getMyAttack() {
//        return myAttack;
//    }
//
//    public void setMyAttack(int myAttack) {
//        this.myAttack = myAttack;
//    }
    public String toString(){
        return "Attack";
    }
}
