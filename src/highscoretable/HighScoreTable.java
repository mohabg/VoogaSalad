package highscoretable;

import java.util.*;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class HighScoreTable extends VBox {
	Comparator comparator;

	public HighScoreTable(HighScore... scores) {
		super();
		comparator = (o1, o2) -> ((HighScore) o1).getScore().compareTo(((HighScore) o2).getScore());

		getChildren().addAll(Arrays.asList(scores));
		
		getChildren().setAll(getChildren().sorted(comparator));
	}

	public void addScore(HighScore newscore) {
		getChildren().add(newscore);
		getChildren().setAll(getChildren().sorted(comparator));

	}

}
