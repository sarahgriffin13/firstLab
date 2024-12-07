import java.util.LinkedList;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int INITIAL_CAPACITY = 16; // Default initial capacity
    private static final float LOAD_FACTOR = 0.75f; // Load factor for resizing

    private LinkedList<Entry<K, V>>[] table; // Array of linked lists for chaining
    private int size; // Number of entries in the map

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new LinkedList[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[INITIAL_CAPACITY];
        table = newTable;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (LinkedList<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (entry.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        LinkedList<Entry<K, V>> bucket = table[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
        if ((float) size / table.length > LOAD_FACTOR) {
            resize();
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V value = entry.getValue();
                    bucket.remove(entry);
                    size--;
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        LinkedList<Entry<K, V>> entries = new LinkedList<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                entries.addAll(bucket);
            }
        }
        return entries;
    }

    // Helper methods
    private int getIndex(K key) {
        if (key == null) {
            return 0; // Special handling for null key
        }
        return Math.abs(key.hashCode()) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        size = 0; // Size will be updated as entries are rehashed into the new table

        // Rehash all entries to the new table
        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    // Recalculate the index in the new table
                    int index = getIndex(entry.getKey());
                    if (table[index] == null) {
                        table[index] = new LinkedList<>();
                    }
                    table[index].add(entry); // Add the entry to the new table
                    size++; // Increment size
                }
            }
        }
    }

    // Nested Entry class
    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
