public interface MyMap<K, V> {
    int size(); // Returns the number of key-value pairs in the map
    boolean isEmpty(); // Checks if the map is empty
    void clear(); // Clears the map
    boolean containsKey(K key); // Checks if the map contains the given key
    boolean containsValue(V value); // Checks if the map contains the given value
    V put(K key, V value); // Adds a key-value pair to the map
    V get(K key); // Retrieves the value associated with the given key
    V remove(K key); // Removes the key-value pair for the given key
    Iterable<Entry<K, V>> entrySet(); // Returns an iterable collection of all key-value pairs

    // Entry interface to define key-value pairs
    interface Entry<K, V> {
        K getKey();
        V getValue();
        void setValue(V value);
    }
}
