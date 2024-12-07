import java.util.*;

public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private List<Entry<K, V>>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new ArrayList[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }

        // Check if key already exists
        for (Entry<K, V> entry : buckets[index]) {
            if (Objects.equals(entry.key, key)) {
                entry.value = value;
                return;
            }
        }

        // Add new entry
        buckets[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (Objects.equals(entry.key, key)) {
                    return entry.value;
                }
            }
        }
        
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        
        if (buckets[index] != null) {
            Iterator<Entry<K, V>> iterator = buckets[index].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (Objects.equals(entry.key, key)) {
                    V value = entry.value;
                    iterator.remove();
                    size--;
                    return value;
                }
            }
        }
        
        return null;
    }

    public int size() {
        return size;
    }

    private int getIndex(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % buckets.length);
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}