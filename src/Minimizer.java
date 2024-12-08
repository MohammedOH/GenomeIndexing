import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Minimizer extends SeedAlgorithm {

    private Integer k;
    private Integer w;

    private String[] kMers;
    private SeedMap seeds;
    private HashTable hashTable;

    public Minimizer() {
    }

    public Minimizer(String sequence, Integer k, Integer w) {
        setSequence(sequence);
        this.k = k;
        this.w = w;
    }

    private String[] extractKMers() throws Exception {
        if (getSequence() == null || k == null || w == null) {
            throw new Exception("Attributes: \"sequence\", \"k\", and \"w\" must be set to generate Minimizer Seeds!");
        }

        kMers = new String[getSequence().length() - k + 1];

        for (int i = 0; i < getSequence().length() - k + 1; i++) {
            kMers[i] = getSequence().substring(i, i + k);
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

        seeds = new SeedMap();
        hashTable = new HashTable();

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

    @Override
    public void clear() {
        super.clear();
        this.kMers = null;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;

        // Erase current seeds and hash table
        clear();
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;

        // Erase current seeds and hash table
        clear();
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

    @Override
    public SeedMap getSeeds() throws Exception {
        if (seeds == null) {
            buildMinimizerSeeds();
        }

        return seeds;
    }

    @Override
    public HashTable getHashTable() throws Exception {
        if (hashTable == null) {
            buildMinimizerSeeds();
        }

        return hashTable;
    }

}
