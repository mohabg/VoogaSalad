package gameElements;

public abstract class Attack extends Sprite implements Behavior{
		
		private Movement movement;
		
		public Movement getMovement() {
			return movement;
		}
		public void setMovement(Movement movement) {
			this.movement = movement;
		}
		
		
}
