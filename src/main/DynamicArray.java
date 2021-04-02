package main;


public class DynamicArray<T> implements Iterable<T> {

	private T[] array;
	private int length = 0;
	private int capacity = 0;

	public DynamicArray() {
		this(16);
	}

	public DynamicArray(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Illlegal capacity:" + capacity);
		}
		this.capacity = capacity;
		array = (T[]) new Object[capacity];
	}

	public int size() {
		return length;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public T get(int index) {
		return array[index];
	}

	public void set(int index, T element) {
		array[index] = element;
	}

	public void clear() {
		for (int index = 0; index < capacity; index++) {
			array[index] = null;
		}
		capacity = 0;
	}

	public void add(T element) {
		// Check if we need to resize
		if (isLengthExceedingTheCapacityOfArray()) {
			// resize the array
			increaseTheCapacityOfArray();
			T[] newArray = (T[]) new Object[capacity];
			for (int index = 0; index < capacity; index++) {
				newArray[index] = array[index];
			}
			array = newArray;
		}
		array[length++] = element;
	}

	private void increaseTheCapacityOfArray() {
		if (capacity == 0) {
			capacity = 1;
		} else {
			capacity *= 2;
		}
	}

	private boolean isLengthExceedingTheCapacityOfArray() {
		return length + 1 >= capacity;
	}

	public T removeAt(int indexToRemoveAt) {
		checkIfIndexIsWithinRange(indexToRemoveAt);
		T data = array[indexToRemoveAt];
		T[] newArray = (T[]) new Object[length - 1];
		for (int index = 0, adjustedIndex = 0; index < length; index++, adjustedIndex++) {
			if (index == indexToRemoveAt) {
				adjustedIndex--;
			} else {
				newArray[adjustedIndex] = array[index];
			}
		}
		array = newArray;
		capacity = --length;
		return data;

	}

	private void checkIfIndexIsWithinRange(int indexToRemoveAt) {
		if (indexToRemoveAt >= length && indexToRemoveAt < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	public boolean remove(Object object) {
		for (int index = 0; index < length; index++) {
			if (array[index].equals(object)) {
				removeAt(index);
				return true;
			}
		}
		return false;
	}

	public int indexOf(Object object) {
		for (int index = 0; index < length; index++) {
			if (array[index].equals(object)) {
				return index;
			}
		}
		return -1;
	}

	public boolean contains(Object object) {
		return indexOf(object) != -1;
	}

	// Iterator is still fast but not as fast as iterative for loop
	@Override
	public java.util.Iterator<T> iterator() {
		return new java.util.Iterator<T>() {
			int index = 0;

			public boolean hasNext() {
				return index < length;
			}

			public T next() {
				return array[index++];
			}
		};
	}

	@Override
	public String toString() {
		if (length == 0) {
			return "[]";
		} else {
			StringBuilder output = new StringBuilder(length).append("[");
			for (int index = 0; index < length - 1; index++) {
				output.append(array[index] + ",");
			}
			return output.append(array[length - 1] + "]").toString();
		}
	}

}
