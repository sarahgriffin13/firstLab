import java.util.LinkedList;

public class MyHashMap<K, V> implements MyMap <K, V> {
    private static final int INITIAL_CAPACITY = 16; 
    private static final float LOAD_FACTOR = 0.75f; 
    private LinkedList<Entry<K, V>>[] table;  
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }

    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

   
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i].clear();
        }
        size = 0;
    }

   
    public boolean containsKey(K key) {
        int index = getIndex(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

   
    public boolean containsValue(V value) {
        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

   
    public V put(K key, V value) {
        int index = getIndex(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;

        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
        return null;
    }

 
    public V get(K key) {
        int index = getIndex(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    
    public V remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> list = table[index];
        for (Entry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                list.remove(entry);
                size--;
                return entry.getValue();
            }
        }
        return null;
    }

   
    public Iterable<Entry<K, V>> entrySet() {
        LinkedList<Entry<K, V>> entries = new LinkedList<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            entries.addAll(bucket);
        }
        return entries;
    }

    private int getIndex(K key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[oldTable.length * 2]; 
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            for (Entry<K, V> entry : bucket) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public static class Entry<K, V> implements MyMap.Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key) && value.equals(entry.value);
        }

        @Override
        public int hashCode() {
            return key.hashCode() ^ (value == null ? 0 : value.hashCode());
        }
    }

    public interface MyMap<K, V> {
        int size();
        boolean isEmpty();
        void clear();
        boolean containsKey(K key);
        boolean containsValue(V value);
        V put(K key, V value);
        V get(K key);
        V remove(K key);
        Iterable<Entry<K, V>> entrySet();

        interface Entry<K, V> {
            K getKey();
            V getValue();
            void setValue(V value);
        }
    }
}
