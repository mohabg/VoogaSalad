package highscoretable;

import java.util.*;
import java.util.stream.*;

import javafx.scene.layout.HBox;

public class HighScore extends HBox {
	List<HighScoreItem> items;

	public HighScore(HighScoreItem<Integer> score, HighScoreItem<String> name, HighScoreItem... args) {
		super();

		items = new ArrayList<HighScoreItem>();
		items.add(score);
		items.add(name);
		items.addAll(Arrays.asList(args));
		getChildren().addAll(items.stream().map(i -> i.getNode()).collect(Collectors.toList()));
	}

	public Integer getScore() {
		return (Integer) items.get(0).getItem();
	}

}
