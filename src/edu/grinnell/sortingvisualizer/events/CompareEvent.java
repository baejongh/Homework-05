package edu.grinnell.sortingvisualizer.events;

import java.util.LinkedList;
import java.util.List;

public class CompareEvent<T extends Comparable<T>>  implements SortEvent<T> {

	public int i;
	public int j;
	
	public CompareEvent(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	@Override
	//applies this sort event to the given array.
	public void apply(T[] arr) {
		// TODO Auto-generated method stub
		return;
		
	}

	@Override
	//returns a list containing all of the array indices that this event affects.
	public List<Integer> getAffectedIndices() {
		// TODO Auto-generated method stub
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(i);
		l.add(j);
		return l;
	}

	@Override
	//return true if this event should be emphasized by the visualizer/audibilizer.
	public boolean isEmphasized() {
		// TODO Auto-generated method stub
		return false;
	}

}
