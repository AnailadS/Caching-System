package cachingSystem.classes;

import dataStructures.classes.Pair;

/**
 * Class that adapts the FIFOCache class to the ObservableCache abstract class.
 */
public class ObservableFIFOCache<K, V> extends ObservableCache<K, V> {

    private FIFOCache<K, V> fifocache = new FIFOCache<K, V>();

    @Override
    public V get(final K key) {
        if (fifocache.get(key) == null) {
            cacheListener.onMiss(key);
            cacheListener.onHit(key);
        } else {
            cacheListener.onHit(key);
        }
        return fifocache.get(key);
    }

    @Override
    public void put(final K key, final V value) {
        fifocache.put(key, value);
        clearStaleEntries();
        cacheListener.onPut(key, value);
    }

    @Override
    public int size() {
        return fifocache.size();
    }

    @Override
    public boolean isEmpty() {
        return fifocache.isEmpty();
    }

    @Override
    public V remove(final K key) {
        return fifocache.remove(key);
    }

    @Override
    public void clearAll() {
        fifocache.clearAll();
    }

    @Override
    public Pair<K, V> getEldestEntry() {
        return fifocache.getEldestEntry();
    }
}
