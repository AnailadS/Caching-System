package cachingSystem.classes;

import cachingSystem.interfaces.Cache;
import dataStructures.classes.Pair;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The FIFOCache class can be considered a queue of entries stored in a LinkedHashMap<K, V>.
 */
public class FIFOCache<K, V> implements Cache<K, V> {

    private LinkedHashMap<K, V> cache;

    public FIFOCache() {
        cache = new LinkedHashMap<>();
    }

    /**
     * Get the value associated with a key, or null if the key does not exist in the FIFOcache.
     * @param key
     * @return value associated with a key
     */
    @Override
    public V get(final K key) {
        return cache.get(key);
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
        return size() == 0;
    }

    /**
     * Remove a key if it exists in the cache.
     *
     * @param key the key to be removed
     * @return the value associated with the key, or null if the key was not in the cache
     */
    @Override
    public V remove(final K key) {
        return cache.remove(key);
    }

    /**
     * Clear all the elements from the cache.
     */
    @Override
    public void clearAll() {
        cache.clear();
    }

    /**
     * Return the first entry in the queue.
     *
     * @return the eldest entry
     */
    @Override
    public Pair<K, V> getEldestEntry() {
        if (isEmpty()) {
            return null;
        }

        Map.Entry<K, V> eldest = cache.entrySet().iterator().next();

        return new Pair<K, V>(eldest.getKey(), eldest.getValue());
    }
}
