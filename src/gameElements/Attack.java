package gameElements;

public abstract class Attack implements Behavior{
		
		private Movement movement;
		private Collision collision;
		private int ammunition;
		private boolean readyToShoot;
		
		public Movement getMovement() {
			return movement;
		}
		public void setMovement(Movement movement) {
			this.movement = movement;
		}
		public Collision getCollision() {
			return collision;
		}
		public void setCollision(Collision collision) {
			this.collision = collision;
		}
		public int getAmmunition() {
			return ammunition;
		}
		public void setAmmunition(int ammunition) {
			this.ammunition = ammunition;
		}
		public boolean hasAmmunitionLeft(){
			return ammunition != 0;
		}
		public boolean isReadyToShoot() {
			return readyToShoot;
		}
		public void setReadyToShoot(boolean readyToShoot) {
			this.readyToShoot = readyToShoot;
		}
}
