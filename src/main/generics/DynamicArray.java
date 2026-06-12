package generics;

import java.util.Objects;

/**
 * A growable array of any element type T, backed by a fixed-size array that
 * doubles its capacity when it fills up. A client adds elements without ever
 * declaring or managing a capacity.
 *
 * @param <T> the type of element stored in this array.
 */
public class DynamicArray<T> {

  private T[] arr;   // the underlying fixed-size storage
  private int size;  // how many elements we've added so far

  // arr is private and only ever holds T (added via add/set), so casting the
  // Object[] to T[] is safe even though the compiler cannot verify it; there
  // is no way to create an array of a type parameter directly.
  @SuppressWarnings("unchecked")
  public DynamicArray() {
    arr = (T[]) new Object[10];  // start with room for 10 elements
    size = 0;                    // but the array is logically empty
  }

  public void add(T value) {
    if (size == arr.length) {
      grow();           // out of room — make the backing array bigger first
    }
    arr[size] = value;  // place the value in the next free slot
    size++;             // one more element now lives in the array
  }

  public T get(int index) {
    return arr[index];
  }

  public void set(int index, T value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    arr[index] = value;
  }

  public int size() {
    return size;
  }

  public boolean contains(T value) {
    return indexOf(value) != -1;
  }

  public int indexOf(T value) {
    for (int i = 0; i < size; i++) {
      // Objects.equals compares contents and is null-safe on either side,
      // unlike == (which would compare object references).
      if (Objects.equals(arr[i], value)) {
        return i;   // found at index i
      }
    }
    return -1;      // not found
  }

  // Remove the element at a given index; return what was removed.
  public T remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    T removed = arr[index];
    // Shift later elements one slot left to close the gap, preserving order.
    for (int j = index; j < size - 1; j++) {
      arr[j] = arr[j + 1];
    }
    size--;
    arr[size] = null;   // null, not 0, now that elements are objects
    return removed;
  }

  // Remove the first element equal to value; return whether anything was removed.
  public boolean remove(T value) {
    int i = indexOf(value);
    if (i == -1) {
      return false;   // nothing to remove
    }
    remove(i);        // delegate the shifting to remove(int)
    return true;
  }

  private void grow() {
    // Same cast rationale as the constructor: a fresh Object[] viewed as a T[].
    @SuppressWarnings("unchecked")
    T[] bigger = (T[]) new Object[arr.length * 2];
    for (int i = 0; i < size; i++) {
      bigger[i] = arr[i];
    }
    arr = bigger;
  }
}
