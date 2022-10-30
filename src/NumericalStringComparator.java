import java.util.Comparator;

public class NumericalStringComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        int indexLast$ = s1.lastIndexOf("$");
        double i1 = Double.parseDouble(s1.substring(indexLast$ + 1));
        indexLast$ = s2.lastIndexOf("$");
        double i2 = Double.parseDouble(s2.substring(indexLast$ + 1));
        int cmp = Double.compare(i1, i2);
        if (cmp != 0) {
            return cmp;
        }
        return s1.compareTo(s2);
    }
}
