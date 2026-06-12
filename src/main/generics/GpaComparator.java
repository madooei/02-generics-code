package generics;

import java.util.Comparator;

/** Orders students by GPA, lowest first — an ordering supplied from outside Student. */
public class GpaComparator implements Comparator<Student> {

  @Override
  public int compare(Student a, Student b) {
    return Double.compare(a.getGpa(), b.getGpa());
  }
}
