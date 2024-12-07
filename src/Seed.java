import java.util.Map;
import java.util.Objects;

public class Seed implements Map.Entry<Integer, String>, Comparable<Seed> {

    private final Integer index;
    private String kMer;

    public Seed(Integer index, String kMer) {
        this.index = index;
        this.kMer = kMer;
    }

    @Override
    public Integer getKey() {
        return index;
    }

    @Override
    public String getValue() {
        return kMer;
    }

    @Override
    public String setValue(String kMer) {
        this.kMer = kMer;
        return this.kMer;
    }

    @Override
    public int compareTo(Seed o) {
        return this.index.compareTo(o.index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seed seed = (Seed) o;
        return Objects.equals(index, seed.index) && Objects.equals(kMer, seed.kMer);
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}", kMer, index);
    }
}
