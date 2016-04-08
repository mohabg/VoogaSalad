package gameElements;

public abstract class Attack implements Behavior{
		
		private Movement movement;
		private Collision collision;
		
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
		
}
