package gameElements;

import java.util.Collection;
import java.util.LinkedList;

public class CircularQueue<T> extends LinkedList<T> {


	/**
	 *
	 */

	private static final long serialVersionUID = -3863020733937496042L;

	private int index;
	public CircularQueue() {
		super();
		index = 0;
	}

	public CircularQueue(Collection<? extends T> arg0) {
		super(arg0);
	}
	public T getNext(){
		index++;
		if(this.size() <= index)
			index = 0;
		return this.get(index);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


}

