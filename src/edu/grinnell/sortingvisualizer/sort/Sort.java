package edu.grinnell.sortingvisualizer.sort;

import java.util.Arrays;
import java.util.List;

import edu.grinnell.sortingvisualizer.events.CompareEvent;
import edu.grinnell.sortingvisualizer.events.CopyEvent;
import edu.grinnell.sortingvisualizer.events.SortEvent;
import edu.grinnell.sortingvisualizer.events.SwapEvent;

public class Sort {

	public <T extends Comparable<T>> void eventSort(T[] arr, List<SortEvent<T>> events) {
		
	}
	
	//quicksort
	public static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(T[] arr, int lowerIndex,
			int higherIndex, List<SortEvent<T>> events) {

		if (arr == null || arr.length == 0) {
			return events;
		}

		int i = lowerIndex;
		int j = higherIndex;
		// calculate pivot number, I am taking pivot as middle index number
		T pivot = arr[lowerIndex+(higherIndex-lowerIndex)/2];
		// Divide into two arrays
		while (i <= j) {
			/**
			 * In each iteration, we will identify a number from left side which 
			 * is greater then the pivot value, and also we will identify a number 
			 * from right side which is less then the pivot value. Once the search 
			 * is done, then we exchange both numbers.
			 */
			while (arr[i].compareTo(pivot) < 0) {
				i++;
			}
			while (arr[j].compareTo(pivot) > 0) {
				j--;
			}
			if (i <= j) {
				events.add((SortEvent<T>) new CompareEvent<T>(i,j));
				swap(arr, i, j);
				events.add((SortEvent<T>) new SwapEvent<T>(i,j));
				//move index to next position on both sides
				i++;
				j--;
			}
		}
		// call quickSort() method recursively
		if (lowerIndex < j) {
			events.add((SortEvent<T>) new CompareEvent<T>(lowerIndex,j));
			quickSort(arr, lowerIndex, j, events);
		}
		if (i < higherIndex){
			events.add((SortEvent<T>) new CompareEvent<T>(i,higherIndex));
			quickSort(arr, i, higherIndex, events);
		}
		return events;
	}

	//mergesort from Ryan Galang

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> void merge (T[] arr, int lo, int mid,
			int hi, List<SortEvent<T>> events) {
		Object[] ret = new Object[hi - lo];
		int i = lo;
		int j = mid;
		int k = 0;
		while(i < mid && j < hi) {
			if (arr[i].compareTo(arr[j]) <= 0)  {
				events.add((SortEvent<T>) new CompareEvent<T>(i,j));
				ret[k++] = arr[i++];
				events.add((SortEvent<T>) new CopyEvent<T>(i,arr[i]));
			}
			else {
				events.add((SortEvent<T>) new CompareEvent<T>(i,j));
				ret[k++] = arr[j++];
				events.add((SortEvent<T>) new CopyEvent<T>(j,arr[j]));
			}
		}
		while (i<mid) { 
			ret[k++] = arr[i++];
			events.add((SortEvent<T>) new CopyEvent<T>(i,arr[i]));
		}
		while (j<hi) { 
			ret[k++] = arr[j++];
			events.add((SortEvent<T>) new CopyEvent<T>(j,arr[j]));
		}
		for (int m = 0; m < ret.length; m++)  {
			arr[lo + m] = (T) ret[m];
			events.add((SortEvent<T>) new CopyEvent<T>(m,(T) ret[m]));
		}
	}

	public static <T extends Comparable<T>> List<SortEvent<T>>  mergeSort(T[] arr, 
			List<SortEvent<T>> events)  {
		mergeSort(arr, 0, arr.length, events);
		return events;
	}

	private static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(T[] arr, int lo, int hi,
			List<SortEvent<T>> events)  {
		if (hi - lo > 1)  {
			int mid = (hi + lo) /2;
			mergeSort(arr, lo, mid, events);
			mergeSort(arr, mid, hi, events);
			merge(arr, lo, mid, hi, events);
		}
		return events;
	}

	//bubble sort from http://mathbits.com/MathBits/Java/arrays/Bubble.htm
	public static <T extends Comparable<T>> List<SortEvent<T>> bubbleSort(T[] arr ,
			List<SortEvent<T>> events)
	{
		int j;
		boolean flag = true;   // set flag to true to begin first pass
		T temp;   //holding variable

		while ( flag )
		{
			flag= false;    //set flag to false awaiting a possible swap
			for( j=0;  j < arr.length -1;  j++ )
			{
				if ( arr[j].compareTo(arr[j+1]) > 0)   // change to > for ascending sort
				{
					events.add((SortEvent<T>) new CompareEvent<T>(j,j + 1));
					//swap elements
					swap(arr, j, j + 1);
					events.add((SortEvent<T>) new SwapEvent<T>(j,j + 1));
					flag = true;              //shows a swap occurred  
				} 
			} 
		}
		return events; 
	}

	//insertion sort from: PM's website
	public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(T[] arr,
			List<SortEvent<T>> events) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (arr[j].compareTo(arr[i]) < 0) {
					events.add((SortEvent<T>) new CompareEvent<T>(j,i));
					swap(arr, i, j);
					events.add((SortEvent<T>) new SwapEvent<T>(i,j));
				}
			}
		}
		return events;
	}

	//selection sort from: http://www.java2novice.com/java-sorting-algorithms/selection-sort/

	public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(T[] arr,
			List<SortEvent<T>> events) 
	{
		for (int i = 0; i < arr.length - 1; i++)
		{
			int index = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[j].compareTo(arr[index]) < 0) {
					events.add((SortEvent<T>) new CompareEvent<T>(j,index));
					index = j;
				}
			swap(arr, index, i);
			events.add((SortEvent<T>) new SwapEvent<T>(index,i));
		}
		return events;
	}

	//cocktail sort from: http://www.javacodex.com/Sorting/Cocktail-Sort
	public static <T extends Comparable<T>> List<SortEvent<T>> cocktailSort(T[] arr,
			List<SortEvent<T>> events){
		boolean swapped;
		do {
			swapped = false;
			for (int i =0; i<=  arr.length  - 2;i++) {
				if (arr[i].compareTo(arr[i + 1]) > 0) {
					events.add((SortEvent<T>) new CompareEvent<T>(i, i + 1));
					//test whether the two elements are in the wrong order
					swap(arr, i, i+1);
					events.add((SortEvent<T>) new SwapEvent<T>(i,i+1));
					swapped = true;
				}
			}
			if (!swapped) {
				//we can exit the outer loop here if no swaps occurred.
				break;
			}
			swapped = false;
			for (int i= arr.length - 2;i>=0;i--) {
				if (arr[ i ].compareTo(arr[ i + 1 ]) > 0) {
					events.add((SortEvent<T>) new CompareEvent<T>(i, i + 1));
					swap(arr, i, i+1);
					events.add((SortEvent<T>) new SwapEvent<T>(i,i+1));
					swapped = true;
				}
			}
			//if no elements have been swapped, then the list is sorted
		} while (swapped);
		return events;
	}

	public static void main(String[] args) {

		Integer[] tester = new Integer[] {5, 34, 21, 12, 65, 1};
		for (int element : tester) {
			System.out.println(element);
		}

	}
}
