package gameElements;

import behaviors.Behavior;
import collisions.Collision;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.StringProperty;

public class Enemy implements IEnemy {

	private SpriteProperties myProperties;
	private Health myHealth;
	private ListProperty<Collision> myCollisions;
	private MapProperty<StringProperty, Behavior> behaviors;

	@Override
	public IEnemy clone() {
		return new Enemy();
	}

	@Override
	public double getSpawnProbability() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
