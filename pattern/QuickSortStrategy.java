import java.util.Arrays;

public class QuickSortStrategy implements SortStrategy {
    @Override
    public int[] sort(int[] arr) {
        int[] a = arr.clone();
        Arrays.sort(a);
        return a;
    }
}