package gameElements;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Collection;
import java.util.LinkedList;

public class CircularQueue<T> extends LinkedList<T> {


	/**
	 *
	 */

	private static final long serialVersionUID = -3863020733937496042L;

	private IntegerProperty index;
	public CircularQueue() {
		super();
        index = new SimpleIntegerProperty();
		index.set(0);
	}

	public CircularQueue(Collection<? extends T> arg0) {
		super(arg0);
	}
	public T getNext(){
		index.add(1);
		if(this.size() <= index.getValue())
			index.set(0);
		return this.get(index.getValue());
	}

	public int getIndex() {
		return index.getValue();
	}

	public void setIndex(int index) {
		this.index.set(index);
	}


}

