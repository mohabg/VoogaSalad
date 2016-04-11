package gameElements;

public abstract class Attack extends Sprite implements Behavior{
		
		private Movement movement;
		private double damage;
		public Attack(){
			
		}
		public Attack(Movement movement){
			this.movement = movement;
			damage = 0;
		}
		public Attack(Movement movement, double damage){
			this.movement = movement;
			this.damage = damage;
		}
		public Movement getMovement() {
			return movement;
		}
		public void setMovement(Movement movement) {
			this.movement = movement;
		}
		public double getDamage() {
			return damage;
		}
		public void setDamage(double damage) {
			this.damage = damage;
		}
		
		
}
