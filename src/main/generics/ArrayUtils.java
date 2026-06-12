package generics;

import java.util.Comparator;

/**
 * A namespace of general-purpose algorithms over a DynamicArray. Unlike
 * DynamicArray, this is not a blueprint: it holds no state and is never
 * instantiated. Each algorithm reaches the elements only through the public
 * size, get, and set operations.
 */
public final class ArrayUtils {

  private ArrayUtils() {
    // This class is a namespace, not a blueprint — don't instantiate it.
  }

  public static <T> void swap(DynamicArray<T> a, int i, int j) {
    T temp = a.get(i);
    a.set(i, a.get(j));
    a.set(j, temp);
  }

  // Returns the index of the smallest element in a[from..size), by natural order.
  public static <T extends Comparable<T>> int indexOfMin(DynamicArray<T> a, int from) {
    int minIndex = from;
    for (int i = from + 1; i < a.size(); i++) {
      if (a.get(i).compareTo(a.get(minIndex)) < 0) {
        minIndex = i;
      }
    }
    return minIndex;
  }

  // Sorts a in place by each element's natural order (Comparable).
  public static <T extends Comparable<T>> void selectionSort(DynamicArray<T> a) {
    for (int i = 0; i < a.size() - 1; i++) {
      int min = indexOfMin(a, i);  // find the smallest remaining element
      swap(a, i, min);             // move it into position i
    }
  }

  // Returns the index of the smallest element in a[from..size), by the given order.
  public static <T> int indexOfMin(DynamicArray<T> a, int from, Comparator<T> cmp) {
    int minIndex = from;
    for (int i = from + 1; i < a.size(); i++) {
      if (cmp.compare(a.get(i), a.get(minIndex)) < 0) {
        minIndex = i;
      }
    }
    return minIndex;
  }

  // Sorts a in place by the order supplied in cmp; elements need not be Comparable.
  public static <T> void selectionSort(DynamicArray<T> a, Comparator<T> cmp) {
    for (int i = 0; i < a.size() - 1; i++) {
      int min = indexOfMin(a, i, cmp);
      swap(a, i, min);
    }
  }
}
