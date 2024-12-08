public abstract class SeedAlgorithm {

    private String sequence;
    private SeedMap seeds;
    private HashTable hashTable;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;

        // Erase current seeds and hash table
        clear();
    }

    public abstract HashTable getHashTable() throws Exception;

    public String getHashTableString(String separator) throws Exception {
        if (hashTable == null) {
            hashTable = getHashTable();
        }

        return hashTable.toString().replaceAll("\n", separator);
    }

    public String getHashTableString() throws Exception {
        if (hashTable == null) {
            hashTable = getHashTable();
        }

        return hashTable.toString();
    }

    public abstract SeedMap getSeeds() throws Exception;

    public String getSeedsString(String separator) throws Exception {
        if (seeds == null) {
            seeds = getSeeds();
        }

        return seeds.toString().replaceAll("\n", separator);
    }

    public String getSeedsString() throws Exception {
        if (seeds == null) {
            seeds = getSeeds();
        }

        return seeds.toString();
    }

    public void clear() {
        this.seeds = null;
        this.hashTable = null;
    }
}
