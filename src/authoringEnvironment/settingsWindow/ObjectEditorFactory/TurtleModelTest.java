package authoringEnvironment.settingsWindow.ObjectEditorFactory;

import java.util.Observable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TurtleModelTest extends Observable{
	/**
	 * Relevant model parameters.
	 */
	private DoubleProperty x = new SimpleDoubleProperty();
	private DoubleProperty y = new SimpleDoubleProperty();
	private DoubleProperty orientation = new SimpleDoubleProperty();
	private BooleanProperty penDown = new SimpleBooleanProperty();
	private BooleanProperty visibility = new SimpleBooleanProperty();
	private BooleanProperty isSelected = new SimpleBooleanProperty();
	private IntegerProperty myId = new SimpleIntegerProperty();

	/** TurtleModel Constructor
	 * Sets all relevant parameters for the model from inputted values. 
	 */
	public TurtleModelTest() {
		this(0, 0, 0, false, false, 0);
		x.addListener((o, ov, nv) -> {
			System.out.println(nv.doubleValue());
		});
	}
	
	public TurtleModelTest(double x, double y, double orientation, boolean penDown, boolean isVisable, int ID) {
		this.setX(x);
		this.setY(y);
		this.setOrientation(orientation);
		this.setPenDown(penDown);
		this.setVisibility(isVisable);
		this.isSelected.set(true);
		this.setMyId(ID);
	}

	/** 
	 * Updates all model parameters with the inputed TurtelModel holding the new parameters. Notifies obervers of the change. 
	 */
	public void update(TurtleModelTest newParameters) {
		//System.out.println(newParameters.getX() + " " + newParameters.getY() + " " + newParameters.getOrientation() + " " + newParameters.getPenStatus() + " " + newParameters.getVisibility() + "\n");
		this.setX(newParameters.getX());
		this.setY(newParameters.getY());
		this.setOrientation(newParameters.getOrientation());
		this.setPenDown(newParameters.getPenStatus());
		this.setVisibility(newParameters.getVisibility());
		setChanged();
		notifyObservers(newParameters);  
	}

	/** 
	 * Resets the model to the initial state. 
	 */
	public void reset() {
		TurtleModelTest newParameters = new TurtleModelTest(0, 0 , 0, true, true, myId.getValue());
		update(newParameters);
		isSelected.set(true);
		setChanged();
		notifyObservers(newParameters);
	}
	
	/** 
	 * The following getters and property methods return the cooresponding values to the caller. 
	 */

	public DoubleProperty xLocProperty() {
		return this.x;
	}

	public DoubleProperty yLocProperty() {
		return this.y;
	}

	public DoubleProperty orientationProperty() {
		return this.orientation;
	}

	public BooleanProperty penProperty() {
		return this.penDown;
	}

	public BooleanProperty visibilityProperty() {
		return this.visibility;
	}

	public double getX() {
		return this.xLocProperty().get();
	}

	public double getY() {
		return this.yLocProperty().get();
	}
	
	public boolean isSelected() {
		return isSelected.getValue();
	}

	public double getOrientation() {
		return this.orientationProperty().get() % 360;
	}

	public boolean getPenStatus() {
		return this.penProperty().get();
	}

	public boolean getVisibility() {
		return this.visibilityProperty().get();
	}
	
	public int getMyId() {
		return myId.getValue();
	}

	/** 
	 * The following setter methods set the corresponding values. 
	 */
	
	public void setX(double x) {
		this.xLocProperty().set(x);
	}

	public void setY(double x) {
		this.yLocProperty().set(x);
	}

	public void setOrientation(double orientation) {
		this.orientationProperty().set(orientation);
	}

	public void setPenDown(boolean penDown){
		this.penProperty().set(penDown);
	}

	public void setVisibility(boolean visible) {
		this.visibilityProperty().set(visible);
	}
	
	public void setMyId(int myId) {
		this.myId.set(myId);
	}

	/** 
	 * Creates a copy of the turtle model, used by Parser. 
	 */
	public TurtleModelTest copy(){
		return new TurtleModelTest(this.getX(), this.getY(), this.getOrientation(), this.getPenStatus(), this.getVisibility(), this.getMyId());
	}

	/** 
	 * Returns if the turtle is selected. 
	 */
	public BooleanProperty getIsSelectedProperty(){
		return isSelected;
	}

	/** 
	 * Returns all turtle parameters as an organized string. 
	 */
	@Override
	public String toString(){
		String result = x.getValue() + " " + y.getValue() +" "+ orientation.getValue() +" "+ penDown.getValue()+ " "+ visibility.getValue();
		return result;
	}

	/** 
	 * Changes if the turtle is or isn't selected to the opposite of the current state. 
	 */
	public void changeSelected() {
		if (isSelected.getValue()){
			isSelected.set(false);
		}
		else{
			isSelected.set(true);
		}	
	}
}



