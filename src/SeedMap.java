import java.util.Arrays;
import java.util.HashMap;

public class SeedMap extends HashMap<Integer, String> {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Integer[] arr = keySet().toArray(new Integer[0]);
        Arrays.sort(arr);

        for (Integer i : arr) {
            builder.append(String.format("%s, %d\n", get(i), i));
        }

        return builder.toString();
    }
}
