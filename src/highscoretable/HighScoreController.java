package highscoretable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

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
	public HighScoreController(Collection<HighScore> history){
		this();
		data.addAll(history);
		
	}

	public TableView<HighScore> getTable() {
		return table;
	}
	
	public Collection<HighScore> getList(){
		return data;
	}
	
	public void addHighScore(Double score, String game){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Please enter your name:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(player -> addHighScore(score, player, game));
	}
	public void addHighScore(Double score, String player, String game){
		addHighScores(new HighScore(score, player, game));
	}
	
	public void addHighScores(HighScore... scores) {
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
