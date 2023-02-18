package iterator;

import java.util.ArrayList;

public class FIFOiterator <T> implements Iterator<T> {

	private ArrayList<T> list;
	private Integer curr;
	public FIFOiterator(ArrayList<T> list) {
		this.list = list;
		curr = 0;
	}

	@Override
	public T getNext() {
		if (hasNext()) return list.get(curr++);
		return null;
	}
	@Override
	public boolean hasNext() {
		return curr<list.size();
	}
}
