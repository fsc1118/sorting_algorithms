package sorting_algorithms;

import java.util.Arrays;

public class MergeSort {
	public MergeSort() {};
	public <T extends Comparable<T>> void merge(T arraysSorted[]) {
		if (arraysSorted == null) {
			throw new NullPointerException();
		}
		mergeDivision(arraysSorted, 0, arraysSorted.length - 1);
	}
	private <T extends Comparable<T>> void mergeDivision(T arraysSorted[], int front, int rear) {
		if (front >= rear) {
			return;
		}
		int mid = (front + rear) / 2;
		mergeDivision(arraysSorted, front, mid);
		mergeDivision(arraysSorted, mid + 1, rear);
		mergeTwoOrderedArray(arraysSorted, front, mid, mid + 1, rear);
	}
	private <T extends Comparable<T>> void mergeTwoOrderedArray(T arraysSorted[], int front, int frontEnd, int rear, int rearEnd) {
		int pointerToFront = front;
		int pointerToRear = rear;
		int tempBufferSize = rearEnd - front + 1;
		Object tempBuffer[] = new Object[tempBufferSize];
		int index = 0;
		for (;pointerToFront <= frontEnd && pointerToRear <= rearEnd;) {
			T first = arraysSorted[pointerToFront];
			T second = arraysSorted[pointerToRear];
			if (first.compareTo(second) < 0) {
				tempBuffer[index++] = arraysSorted[pointerToFront++];
			} else {
				tempBuffer[index++] = arraysSorted[pointerToRear++];
			}
		}
		if (pointerToFront > frontEnd) {
			for (int i = pointerToRear; i <= rearEnd; i++) {
				tempBuffer[index++] = arraysSorted[i];
			}
		} else if (pointerToRear > rearEnd) {
			for (int i = pointerToFront; i <= frontEnd; i++) {
				tempBuffer[index++] = arraysSorted[i];
			}
		}
		for (int i = 0, j = front; i < tempBufferSize; i++, j++) {
			arraysSorted[j] = (T) tempBuffer[i];
		}
	}
	public static void main(String[] args) {
		Integer array[] = {1, 2,3,3,4,5,5,6,6,6, 3, 4, 5};
		new MergeSort().merge(array);
		System.out.println(Arrays.toString(array));
	}
}
