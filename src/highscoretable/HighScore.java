package highscoretable;

import javafx.beans.property.*;

public class HighScore {
	SimpleDoubleProperty scorevalue;
	SimpleStringProperty playername;
	SimpleStringProperty gamename;

	public HighScore(Double score, String player, String game) {
		scorevalue = new SimpleDoubleProperty(score);
		playername = new SimpleStringProperty(player);
		gamename = new SimpleStringProperty(game);

	}

	public SimpleDoubleProperty scorevalueProperty() {
		return scorevalue;
	}

	public SimpleStringProperty playernameProperty() {
		return playername;

	}

	public SimpleStringProperty gamenameProperty() {
		return gamename;
	}
	// public Integer getScore() {
	// return (Integer) items.get(0).getItem();
	// }

}
