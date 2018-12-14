import java.util.*;

public class LRUCache2<K, V> extends LinkedHashMap<K, V> {
  private final int maxEntries;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;

  public LRUCache2(int initialCapacity,
                  float loadFactor,
                  int maxEntries) {
    super(initialCapacity, loadFactor, true);
    this.maxEntries = maxEntries;
  }

  public LRUCache2(int initialCapacity,
                  int maxEntries) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR, maxEntries);
  }

  public LRUCache2(int maxEntries) {
    this(DEFAULT_INITIAL_CAPACITY, maxEntries);
  }

  // not very useful constructor
  public LRUCache2(Map<? extends K, ? extends V> m,
                  int maxEntries) {
    this(m.size(), maxEntries);
    putAll(m);
  }

  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size() > maxEntries;
  }

  public static void main(String... args) {
    Map<Integer, String> cache = new LRUCache2<>(5);
    for (int i = 0; i < 10; i++) {
      cache.put(i, "hi");
    }
    // entries 0-4 have already been removed
    // entries 5-9 are ordered
    System.out.println("cache = " + cache);

    cache.get(9);

    System.out.println(cache.get(7));
    // entry 7 has moved to the end
    System.out.println("cache = " + cache);

    for (int i = 10; i < 14; i++) {
      cache.put(i, "hi");
    }
    // entries 5,6,8,9 have been removed (eldest entries)
    // entry 7 is at the beginning now
    System.out.println("cache = " + cache);

    cache.put(42, "meaning of life");
    // entry 7 is gone too
    System.out.println("cache = " + cache);
  }
}