import java.util.*;

public class Minimizer {

    private String sequence;
    private Integer k;
    private Integer w;

    private String[] kMers;
    private Map<Integer, String> seeds;
    private Map<String, Set<Integer>> hashTable;

    public Minimizer() {
    }

    public Minimizer(String sequence, Integer k, Integer w) {
        this.sequence = sequence;
        this.k = k;
        this.w = w;
    }

    private String[] extractKMers() throws Exception {
        if (sequence == null || k == null || w == null) {
            throw new Exception("Attributes: \"sequence\", \"k\", and \"w\" must be set to generate Minimizer Seeds!");
        }

        kMers = new String[sequence.length() - k + 1];

        for (int i = 0; i < sequence.length() - k + 1; i++) {
            kMers[i] = sequence.substring(i, i + k);
        }

        return kMers;
    }

    private void buildMinimizerSeeds() throws Exception {
        if (kMers == null) {
            extractKMers();
        }

        if (w <= 0 || w > kMers.length) {
            throw new Exception("Invalid window size!");
        }

        PriorityQueue<Seed> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Seed a, Seed b) {
                return a.getValue().compareTo(b.getValue());
            }
        });

        seeds = new HashMap<>();
        hashTable = new HashMap<>();

        for (int i = 0; i < kMers.length; i++) {
            pq.add(new Seed(i, kMers[i]));

            if (i < w - 1) {
                continue;
            }

            Seed seed = pq.peek();
            seeds.put(seed.getKey(), seed.getValue());

            if (hashTable.get(seed.getValue()) == null) {
                hashTable.put(seed.getValue(), new HashSet<>());
            }
            hashTable.get(seed.getValue()).add(seed.getKey());

            pq.remove(new Seed(i - w + 1, kMers[i - w + 1]));
        }
    }

    public void clearKMers() {
        this.kMers = null;
        this.seeds = null;
        this.hashTable = null;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;

        // Erase current seeds and hash table
        clearKMers();
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;

        // Erase current seeds and hash table
        clearKMers();
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;

        // Erase current seeds and hash table
        clearKMers();
    }

    public String[] getKMers() throws Exception {
        if (kMers == null) {
            return extractKMers();
        }

        return kMers;
    }

    public String getKMersString() throws Exception {
        if (kMers == null) {
            getKMers();
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < kMers.length; i++) {
            builder.append(String.format("%d\t%s%s\n", i, " ".repeat(i), kMers[i]));
        }

        return builder.toString();
    }


    public Map<Integer, String> getSeeds() throws Exception {
        if (seeds == null) {
            buildMinimizerSeeds();
        }

        return seeds;
    }

    public Map<String, Set<Integer>> getHashTable() throws Exception {
        if (hashTable == null) {
            buildMinimizerSeeds();
        }

        return hashTable;
    }

    public String getSeedsString(String separator) throws Exception {
        if (seeds == null) {
            getSeeds();
        }

        StringBuilder builder = new StringBuilder();

        Integer[] arr = seeds.keySet().toArray(new Integer[0]);
        Arrays.sort(arr);

        for (Integer i : arr) {
            builder.append(String.format("%s, %d%s", seeds.get(i), i, separator == null ? "\n" : separator));
        }

        return builder.toString();
    }

    public String getSeedsString() throws Exception {
        return getSeedsString("\n");
    }

    public String getHashTableString(String separator) throws Exception {
        if (hashTable == null) {
            getHashTable();
        }

        String[] arr = hashTable.keySet().toArray(new String[0]);
        Arrays.sort(arr, new Comparator<>() {
            @Override
            public int compare(String a, String b) {
                Integer minA = Collections.min(hashTable.get(a));
                Integer minB = Collections.min(hashTable.get(b));

                return minA.compareTo(minB);
            }
        });

        StringBuilder builder = new StringBuilder();
        for (String seed : arr) {
            builder.append(
                    String.format("%s, %s%s", seed, hashTable.get(seed), separator == null ? "\n" : separator)
            );
        }

        return builder.toString();
    }

    public String getHashTableString() throws Exception {
        return getHashTableString("\n");
    }

}
