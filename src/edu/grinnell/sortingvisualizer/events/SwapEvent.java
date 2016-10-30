package edu.grinnell.sortingvisualizer.events;

import java.util.LinkedList;
import java.util.List;

public class SwapEvent<T extends Comparable<T>>  implements SortEvent<T> {

	int i, j;
	
	public SwapEvent(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	@Override
	public void apply(T[] arr) {
		// TODO Auto-generated method stub
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	@Override
	public List<Integer> getAffectedIndices() {
		// TODO Auto-generated method stub
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(i);
		l.add(j);
		return l;
	}

	@Override
	public boolean isEmphasized() {
		// TODO Auto-generated method stub
		return true;
	}

}
