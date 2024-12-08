import java.util.*;

public class HashTable extends HashMap<String, Set<Integer>> {
    @Override
    public String toString() {
        String[] arr = keySet().toArray(new String[0]);
        Arrays.sort(arr, new Comparator<>() {
            @Override
            public int compare(String a, String b) {
                Integer minA = Collections.min(get(a));
                Integer minB = Collections.min(get(b));

                return minA.compareTo(minB);
            }
        });

        StringBuilder builder = new StringBuilder();
        for (String seed : arr) {
            List<Integer> indices = new ArrayList<>(get(seed).stream().toList());
            Collections.sort(indices);

            builder.append(String.format("%s, %s\n", seed, indices));
        }

        return builder.toString();
    }
}
