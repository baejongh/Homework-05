package edu.grinnell.sortingvisualizer.events;

import java.util.List;

public interface SortEvent<T> {
	public void apply(T[] arr);
	public List<Integer> getAffectedIndices();
	public boolean isEmphasized();
}
