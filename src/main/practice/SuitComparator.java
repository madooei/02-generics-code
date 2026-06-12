package practice;

import java.util.Comparator;

/** Orders cards by suit, then by rank — an ordering supplied from outside Card. */
public class SuitComparator implements Comparator<Card> {

  @Override
  public int compare(Card a, Card b) {
    if (a.getSuit() != b.getSuit()) {
      return Character.compare(a.getSuit(), b.getSuit());
    }
    return Integer.compare(a.getRank(), b.getRank());
  }
}
