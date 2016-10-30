package edu.grinnell.sortingvisualizer.events;

import java.util.LinkedList;
import java.util.List;

public class CopyEvent<T extends Comparable<T>>  implements SortEvent<T> {

	int i;
	T element;

	public CopyEvent(int i, T element) {
		this.i = i;
		this.element = element;
	}
	
	@Override
	public void apply(T[] arr) {
		// TODO Auto-generated method stub
		element = arr[i];	
	}

	@Override
	public List<Integer> getAffectedIndices() {
		// TODO Auto-generated method stub
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(i);
		return l;
	}

	@Override
	public boolean isEmphasized() {
		// TODO Auto-generated method stub
		return true;
	}

}
