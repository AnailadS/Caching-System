package cachingSystem.classes;

import dataStructures.classes.EfficientLinkedList;
import dataStructures.classes.Node;
import dataStructures.classes.Pair;

/**
 * The entries of the cache are stored in an EfficientLinkedList<K, V>.
 */
public class LRUCache<K, V> extends ObservableCache<K, V> {

    private EfficientLinkedList<K, V> cache = new EfficientLinkedList<K, V>();

    /**
     * Get the value associated with a key, or null if the key does not exist in the cache.
     * @param key
     * @return value associated with a key
     */
    @Override
    public V get(final K key) {
        Node<K, V> node = cache.get(key);
        if (node != null) {
            cacheListener.onHit(key);
            cache.put(key, node.getValue());
            return node.getValue();
        } else {
            cacheListener.onMiss(key);
            cacheListener.onHit(key);
            return cache.get(key).getValue();
        }
    }

    /**
     * Insert a key value pair in the cache.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(final K key, final V value) {
        cache.put(key, value);
        clearStaleEntries();
        cacheListener.onPut(key, value);

    }

    /**
     * The cache size is defined as the number of stored key-value pairs.
     *
     * @return the cache size
     */
    @Override
    public int size() {
        return cache.size();
    }

    /**
     * Tells whether or not the cache is empty.
     *
     * @return @true if the cache has no keys stored, @false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    /**
     * Removes the entry associated with key.
     * @param The key to be removed.
     * @return The value associated with the key, or null if the key doesn't exist in the cache.
     */
    @Override
    public V remove(final K key) {
        Node<K, V> node = cache.remove(key);
        if (node != null) {
            return node.getValue();
        }
        return null;
    }

    /**
     * Removes all of the cache entries.
     */
    @Override
    public void clearAll() {
        cache.clearAll();
    }

    /**
     * @return The entry that hasn't been updated in the longest time or null if the cache is empty.
     */
    @Override
    public Pair<K, V> getEldestEntry() {
        Node<K, V> node = cache.get(cache.getHead());
        if (node != null) {
            return new Pair<K, V>(node.getKey(), node.getValue());
        }
        return null;
    }

    public final EfficientLinkedList<K, V> getCache() {
        return cache;
    }

    public final void setCache(final EfficientLinkedList<K, V> cache) {
        this.cache = cache;
    }

}
