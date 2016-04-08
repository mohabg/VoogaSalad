package highscoretable;

import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HighScoreController {
	TableView<HighScore> table;
	ObservableList<HighScore> data;

	public HighScoreController() {
		table = new TableView<HighScore>();
		data = FXCollections.observableArrayList();
		makeTable();

	}

	public TableView<HighScore> getTable() {
		return table;
	}

	public void addHighScore(HighScore... scores) {
		data.addAll(scores);

	}

	private void makeTable() {
		table.setItems(data);
		table.getColumns().setAll(makeTableCol("scorevalue"), makeTableCol("playername"), makeTableCol("gamename"));

	}

	private TableColumn<HighScore, String> makeTableCol(String name) {
		TableColumn<HighScore, String> col = new TableColumn<HighScore, String>(name);
		col.setCellValueFactory(new PropertyValueFactory(name));
		return col;
	}

}
