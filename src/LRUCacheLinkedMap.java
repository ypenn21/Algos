import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedMap extends LinkedHashMap {

    private final int maxEntries;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public LRUCacheLinkedMap(int maxEntries) {
        super(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, true);
        this.maxEntries = maxEntries;
    }

//    public LRUCacheLinkedMap(int initialCapacity,
//                    int maxEntries) {
//        this(initialCapacity, DEFAULT_LOAD_FACTOR, maxEntries);
//    }
//
//    public LRUCacheLinkedMap(int maxEntries) {
//        this(DEFAULT_INITIAL_CAPACITY, maxEntries);
//    }
//
//    // not very useful constructor
//    public LRUCacheLinkedMap(Map m,
//                    int maxEntries) {
//        this(m.size(), maxEntries);
//        putAll(m);
//    }

    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > maxEntries;
    }

}
