package practice;

import generics.DynamicArray;

/**
 * A small demo of the two practice solutions: a Card type with value equality
 * and orderings, and a generic max over a DynamicArray.
 */
public final class PracticeMain {

  private PracticeMain() {
    // Entry-point holder — not instantiated.
  }

  public static void main(String[] args) {
    // A DynamicArray<Card> — the chapter's generic class, holding the new type.
    DynamicArray<Card> hand = new DynamicArray<>();
    hand.add(new Card(14, 'C'));   // A of clubs
    hand.add(new Card(9, 'H'));    // 9 of hearts
    hand.add(new Card(13, 'H'));   // K of hearts
    hand.add(new Card(7, 'D'));    // 7 of diamonds
    System.out.println("hand:");
    print(hand);

    // contains works because Card overrides equals (by rank AND suit), so a
    // freshly built Card with the same rank and suit counts as already present.
    Card probe = new Card(9, 'H');
    System.out.println("contains 9 of hearts? " + hand.contains(probe));

    // Natural order: Card.compareTo ranks by value, so the A of clubs wins.
    System.out.println("max by natural order (rank, then suit): " + MoreArrayUtils.max(hand));

    // External order: a Comparator ranks by suit first, so the highest heart
    // (the K) wins instead — a different answer from the same hand.
    System.out.println("max by SuitComparator (suit, then rank): "
        + MoreArrayUtils.max(hand, new SuitComparator()));

    // The same generic max works for any comparable type — here, Integers.
    DynamicArray<Integer> scores = new DynamicArray<>();
    scores.add(50);
    scores.add(90);
    scores.add(70);
    System.out.println("max score: " + MoreArrayUtils.max(scores));
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
