package sorting_algorithms;

import java.util.Arrays;

public class InsertSort {
	public InsertSort(){};
	public <T extends Comparable<T>> void insertSort(T arraysSorted[]) {
		insertSort(arraysSorted, arraysSorted.length - 1);
	}
	private <T extends Comparable<T>> void insertSort(T arraysSorted[], int endIndex) {
		if (endIndex == 0) {
			return;
		}
		insertSort(arraysSorted, endIndex - 1);
		insert(arraysSorted, endIndex);
	}
	private <T extends Comparable<T>> void insert(T arraysSorted[], int indexOfInsertion) {
		T valueBringInserted = arraysSorted[indexOfInsertion];
		for (int i = 0; i < indexOfInsertion; i++) {
			T value = arraysSorted[i];
			if (value.compareTo(valueBringInserted) > 0) {
				moveRight(arraysSorted, i, indexOfInsertion);
				arraysSorted[i] = valueBringInserted;
				break;
			}
			
		}
	}
	private <T extends Comparable<T>> void moveRight(T arraysSorted[], int from, int to) {
		for (int i = to; i > from; i--) {
			arraysSorted[i] = arraysSorted[i - 1];
		}
	}
	public static void main(String[] args) {
		Integer array[] = {5,3,3,3,2,1,54,3,2,1};
		new InsertSort().insertSort(array);
		System.out.println(Arrays.toString(array));
	}
}