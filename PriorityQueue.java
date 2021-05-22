package sorting_algorithms;

import java.util.Arrays;

public class PriorityQueue {
	private final int CAPACITY = 17;
	private int size;
	private int[] queue;
	
	public void insert(int i) {
		queue[++size] = i;
		up(size);
	}
	
	public int delMax() throws Exception {
		if (isEmpty()) {
			throw new Exception("Invalid operation on empty queue");
		}
		exch(1, size);
		int returnedValue = queue[size];
		size--;
		sink(1);
		return returnedValue;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(queue);
	}
	public int size() {
		return size;
	}
	
	public PriorityQueue() {
		queue = new int[CAPACITY];
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private void exch(int position1, int position2) {
		int temp = queue[position1];
		queue[position1] = queue[position2];
		queue[position2] = temp;
	}
	
	private void up(int position) {
		if (position == 1) {
			return;
		}
		if (queue[position / 2] < queue[position]) {
			exch(position / 2, position);
			up(position / 2);
		}
	}
	
	private void sink(int position) {
		if (!checkOutBound(position * 2) && queue[position] < queue[position * 2]) {
			exch(position, position * 2);
			sink(position * 2);
		}
		if (!checkOutBound(position * 2 + 1) && queue[position] < queue[position * 2 + 1]) {
			exch(position, position * 2 + 1);
			sink(position * 2 + 1);
		}
	}
	
	private boolean checkOutBound(int position) {
		return position > size || position < 1;
	}
	
	public static void main(String[] args) throws Exception {
		PriorityQueue pq = new PriorityQueue();
		pq.insert(1);
		pq.insert(2);
		pq.insert(19);
		pq.insert(5);
		pq.insert(45);
		pq.insert(55);
		pq.insert(57);
		pq.insert(456);
		pq.insert(552);
		pq.insert(8);
		System.out.println(pq.delMax());
		System.out.println(pq.delMax());
		System.out.println(pq.delMax());
		System.out.println(pq.delMax());
		System.out.println(pq.delMax());
		System.out.println(pq.delMax());
		System.out.println(pq.delMax());
		System.out.println(pq.delMax());
		System.out.println(pq.delMax());
		System.out.println(pq.delMax());
	}
}
