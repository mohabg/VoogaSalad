package gameElements;


public class SpriteProperties {

private double x;
private double y;
private double width;
private double height;
private double angle;
private double xVel;
private double yVel;


	public SpriteProperties() {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
	}

	public SpriteProperties(double x, double y, double angle, double xVel, double yVel){
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.xVel = xVel;
		this.yVel = yVel;
	}
	public SpriteProperties(double x, double y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public void incrementX(){
		this.x++;
	}
	
	public void decrementX(){
		this.x--;
	}
	public void incrementY(){
		this.y++;
	}
	public void decrementY(){
		this.y--;
	}
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	public double getxVel() {
		return xVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public SpriteProperties(double x, double y){
		this(x, y, 0);
	}

	public double getX() {
		return x;
	}

	public void setCoord(double x, double y){
		setX(x);
		setY(y);
	}
	public void setVelocity(double xVel, double yVel){
		setxVel(x);
		setyVel(y);
	}
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public double getDistance(SpriteProperties otherVect){
		return Math.sqrt(( Math.pow(x,2) - Math.pow(otherVect.getX(),2))
				+ (Math.pow(y,2) - Math.pow(otherVect.getY(), 2)));
	}


	public void setAngle(double angle) {
		this.angle = angle;
	}

}
