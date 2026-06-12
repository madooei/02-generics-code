package generics;

/**
 * A small demo of the generic DynamicArray, Comparable, and Comparator that you
 * can run to see the chapter's code work.
 */
public final class Main {

  private Main() {
    // Entry-point holder — not instantiated.
  }

  public static void main(String[] args) {
    // One class, any element type: here a roster of Student objects.
    DynamicArray<Student> roster = new DynamicArray<>();
    roster.add(new Student(3, "Ada", 3.9));
    roster.add(new Student(1, "Linus", 3.4));
    roster.add(new Student(2, "Grace", 4.0));
    System.out.println("roster:");
    print(roster);

    // contains works because Student overrides equals (comparing by id), so a
    // freshly built Student with the same id counts as already on the roster.
    Student probe = new Student(1, "Linus", 3.4);
    System.out.println("contains student #1? " + roster.contains(probe));

    // Natural order: Student.compareTo orders by id.
    ArrayUtils.selectionSort(roster);
    System.out.println("after selectionSort (natural order, by id):");
    print(roster);

    // External order: a Comparator orders by GPA without touching Student.
    ArrayUtils.selectionSort(roster, new GpaComparator());
    System.out.println("after selectionSort (by GPA, lowest first):");
    print(roster);

    // The same class holds Integers — but watch the remove overload trap.
    DynamicArray<Integer> scores = new DynamicArray<>();
    scores.add(50);
    scores.add(60);
    scores.add(70);
    scores.remove(2);   // remove(int): removes the element at INDEX 2 (the 70)
    System.out.println("scores after remove(2) — the index overload removed the 70:");
    print(scores);
    scores.remove(Integer.valueOf(50));   // remove(T): removes the VALUE 50
    System.out.println("scores after remove(Integer.valueOf(50)) — the value overload:");
    print(scores);
  }

  // Prints the elements as [a, b, c], reaching them only through public operations.
  private static <T> void print(DynamicArray<T> a) {
    StringBuilder str = new StringBuilder();
    str.append("[");
    for (int i = 0; i < a.size(); i++) {
      str.append(a.get(i));
      if (i < a.size() - 1) {
        str.append(", ");
      }
    }
    str.append("]");
    System.out.println(str.toString());
  }
}
