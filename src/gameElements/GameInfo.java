package gameElements;

import java.util.Date;

/**
 * Describes the information of the game, including the date the game was created, the game author, and the game title. 
 */


public class GameInfo {
	
	private static String DEFAULT_TITLE = "New Game";
	private static String DEFAULT_AUTHOR = "User";
	
    private String gameTitle;
	private String gameAuthor;
    private Date dateCreated;
    private Date dateUpdated;
    
    public GameInfo() {
    	gameTitle = DEFAULT_TITLE;
    	gameAuthor = DEFAULT_AUTHOR;
    }
    
    public GameInfo(String title, String author) {
    	gameTitle = title;
    	gameAuthor = author;
    }
    
	public String getGameTitle() {
		return gameTitle;
	}
	
    public void setGameTitle(String title) {
        this.gameTitle = title;
    }
    
    public String getGameAuthor() {
		return gameAuthor;
	}

	public void setGameAuthor(String author) {
		gameAuthor = author;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date created) {
		dateCreated = created;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date updated) {
		dateUpdated = updated;
	}


}