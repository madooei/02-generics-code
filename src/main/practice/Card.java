package practice;

import java.util.Objects;

/**
 * A playing card: a rank (2–14, where 11–14 are J, Q, K, A) and a suit, one of
 * 'C', 'D', 'H', 'S'. Two cards are equal when both their rank and suit match,
 * and a card's natural order is by rank, then by suit — a total order that
 * stays consistent with equals.
 */
public class Card implements Comparable<Card> {
  private int rank;
  private char suit;

  public Card(int rank, char suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public int getRank() {
    return rank;
  }

  public char getSuit() {
    return suit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card other = (Card) o;
    return rank == other.rank && suit == other.suit;
  }

  @Override
  public int hashCode() {
    // Both fields decide equality, so both must feed the hash.
    return Objects.hash(rank, suit);
  }

  @Override
  public int compareTo(Card other) {
    if (this.rank != other.rank) {
      return Integer.compare(this.rank, other.rank);
    }
    // Same rank: break the tie by suit so the order is total and matches equals.
    return Character.compare(this.suit, other.suit);
  }

  @Override
  public String toString() {
    return rankName() + suit;
  }

  // Face cards print as letters; everything else prints as its number.
  private String rankName() {
    switch (rank) {
      case 11: return "J";
      case 12: return "Q";
      case 13: return "K";
      case 14: return "A";
      default: return Integer.toString(rank);
    }
  }
}
