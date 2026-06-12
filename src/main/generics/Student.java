package generics;

/**
 * A student record. Two students are equal when their ids match, and a
 * student's natural order is by id as well — keeping equals and compareTo
 * consistent.
 */
public class Student implements Comparable<Student> {
  private int id;
  private String name;
  private double gpa;

  public Student(int id, String name, double gpa) {
    this.id = id;
    this.name = name;
    this.gpa = gpa;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student other = (Student) o;
    return id == other.id;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(id);
  }

  @Override
  public int compareTo(Student other) {
    return Integer.compare(this.id, other.id);
  }

  @Override
  public String toString() {
    return name + " (#" + id + ", GPA " + gpa + ")";
  }
}
