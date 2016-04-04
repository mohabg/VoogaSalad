package highscoretable;

import java.util.*;

import javafx.scene.layout.VBox;

public class HighScoreTable extends VBox {
	Comparator c;

	public HighScoreTable(HighScore... scores) {
		super();
		c = (o1, o2) -> ((HighScore) o1).getScore().compareTo(((HighScore) o2).getScore());

		getChildren().addAll(Arrays.asList(scores));
		getChildren().sort(c);
	}

	public void addScore(HighScore newscore) {
		getChildren().add(newscore);
		getChildren().sort(c);
	}

}
