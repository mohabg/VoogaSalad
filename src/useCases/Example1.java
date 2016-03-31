package useCases;


public class Example1 {
	private DisplayWindow myDisplay;
	private int myNumLevels;
	
	private void implement(int levels){
		myDisplay = new DisplayWindow();
		myNumLevels = levels;
		
		/*
		 * The player will interact with a dropdown of some sort of input area of some sort and choose the number of levels to create.
		 * With our game design, each level of the game is a separate tab of the DisplayWindow class.
		 * Thus, once the number of levels is chosen by the user, the corresponding number of tabs will be created.
		 */
		
		myDisplay.createLevelsTabPane(myNumLevels);
	}


}
