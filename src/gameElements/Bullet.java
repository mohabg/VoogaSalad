
package gameElements;

public class Bullet extends Attack{

    private Actor mySprite;
    private double numberOfBullets;
    private double rechargeTime;

    public Bullet(double numBullets, double rechargeTime){

    }
    public Bullet(Actor sprite){
        mySprite = sprite;
    }
    public double getNumberOfBullets() {
        return numberOfBullets;
    }
    public void setNumberOfBullets(double numberOfBullets) {
        this.numberOfBullets = numberOfBullets;
    }
    public double getRechargeTime() {
        return rechargeTime;
    }
    public void setRechargeTime(double rechargeTime) {
        this.rechargeTime = rechargeTime;
    }
    public void decrementRechargeTime(double decrement){
        this.rechargeTime -= decrement;
    }
    @Override
    public void apply(Sprite sprite) {
        if(ready()){
            this.setCoord(mySprite.getX(), mySprite.getY());
            getMovement().apply(this);
        }
    }
    public boolean ready(){
        return numberOfBullets > 0 && rechargeTime <= 0;
    }
}