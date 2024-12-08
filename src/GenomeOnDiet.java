import java.util.*;

public class GenomeOnDiet extends SeedAlgorithm {

    private Integer k;
    private Integer w;
    private String p;

    private String patternedSequence;
    private List<Integer> originalIndices;
    private String[] kMers;
    private SeedMap seeds;
    private HashTable hashTable;

    public GenomeOnDiet() {
        originalIndices = new ArrayList<>();
    }

    public GenomeOnDiet(String sequence, Integer k, Integer w, String p) {
        setSequence(sequence);
        this.k = k;
        this.w = w;
        this.p = p;

        originalIndices = new ArrayList<>();
    }

    private String buildPatternedSequence() throws Exception {
        if (getSequence() == null || k == null || w == null || p == null) {
            throw new Exception("Attribute \"sequence\" must be set to generate the new sequence!");
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getSequence().length(); i++) {
            if (p.charAt(i % p.length()) == '1') {
                builder.append(getSequence().charAt(i));
                originalIndices.add(i);
            }
        }
        patternedSequence = builder.toString();

        return patternedSequence;
    }

    private String[] extractKMers() throws Exception {
        if (getSequence() == null || k == null || w == null || p == null) {
            throw new Exception("Attributes: \"sequence\", \"k\", \"w\", and \"p\" must be set to generate Genome-on-Diet Seeds!");
        }

        if (patternedSequence == null) {
            patternedSequence = getPatternedSequence();
        }

        kMers = new String[patternedSequence.length() - k + 1];
        for (int i = 0; i < patternedSequence.length() - k + 1; i++) {
            kMers[i] = patternedSequence.substring(i, i + k);
        }

        return kMers;
    }

    private void buildSeeds() throws Exception {
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
            pq.add(new Seed(originalIndices.get(i), kMers[i]));

            if (i < w - 1) {
                continue;
            }

            Seed seed = pq.peek();
            seeds.put(seed.getKey(), seed.getValue());

            if (hashTable.get(seed.getValue()) == null) {
                hashTable.put(seed.getValue(), new HashSet<>());
            }
            hashTable.get(seed.getValue()).add(seed.getKey());

            pq.remove(new Seed(originalIndices.get(i - w + 1), kMers[i - w + 1]));
        }
    }

    @Override
    public void clear() {
        super.clear();
        this.patternedSequence = null;
        this.originalIndices = new ArrayList<>();
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

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;

        // Erase current seeds and hash table
        clear();
    }

    public String getPatternedSequence() throws Exception {
        if (patternedSequence == null) {
            return buildPatternedSequence();
        }

        return patternedSequence;
    }

    public String getPatternedSequenceString() throws Exception {
        if (getSequence() == null || k == null || w == null || p == null) {
            throw new Exception("Attribute \"sequence\" must be set to generate the new sequence!");
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getSequence().length(); i++) {
            if (p.charAt(i % p.length()) == '1') {
                builder.append(getSequence().charAt(i));
            } else {
                builder.append('-');
            }
        }

        return builder.toString();
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
            builder.append(String.format("%d\t%s%s\n", originalIndices.get(i), " ".repeat(i), kMers[i]));
        }

        return builder.toString();
    }

    @Override
    public SeedMap getSeeds() throws Exception {
        if (seeds == null) {
            buildSeeds();
        }

        return seeds;
    }

    @Override
    public HashTable getHashTable() throws Exception {
        if (hashTable == null) {
            buildSeeds();
        }

        return hashTable;
    }

}
