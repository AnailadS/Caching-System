package observerPattern.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import observerPattern.interfaces.CacheListener;

/**
 * The KeyStatsListener collects key-level stats for cache operations.
 *
 * @param <K>
 * @param <V>
 */
public class KeyStatsListener<K, V> implements CacheListener<K, V> {

    private Map<K, Integer> hits = new HashMap<K, Integer>();
    private Map<K, Integer> misses = new HashMap<K, Integer>();
    private Map<K, Integer> updates = new HashMap<K, Integer>();

    /**
     * Get the number of hits for a key.
     *
     * @param key the key
     * @return number of hits
     */
    public int getKeyHits(final K key) {
        if (hits.get(key) != null) {
            return hits.get(key);
        } else {
            return 0;
        }
    }

    /**
     * Get the number of misses for a key.
     *
     * @param key the key
     * @return number of misses
     */
    public int getKeyMisses(final K key) {
        return misses.get(key);
    }

    /**
     * Get the number of updates for a key.
     *
     * @param key the key
     * @return number of updates
     */
    public int getKeyUpdates(final K key) {
        return updates.get(key);
    }

    /**
     * Get the @top most hit keys.
     *
     * @param top number of top keys
     * @return the list of keys
     */
    public List<K> getTopHitKeys(final int top) {
        MapTool<K, Integer> sortMyHits = new MapTool<K, Integer>();
        return sortMyHits.mapSort(hits).subList(0, top);
    }

    /**
     * Get the @top most missed keys.
     *
     * @param top number of top keys
     * @return the list of keys
     */
    public List<K> getTopMissedKeys(final int top) {
        MapTool<K, Integer> sortMyMisses = new MapTool<K, Integer>();
        return sortMyMisses.mapSort(misses).subList(0, top);
    }

    /**
     * Get the @top most updated keys.
     *
     * @param top number of top keys
     * @return the list of keys
     */
    public List<K> getTopUpdatedKeys(final int top) {
        MapTool<K, Integer> sortMyUpdates = new MapTool<K, Integer>();
        return sortMyUpdates.mapSort(updates).subList(0, top);
    }

    /**
     * Increments the number of hits for the key if it's not the first hit. Otherwise inserts it.
     * @param key
     */
    @Override
    public void onHit(final K key) {
        if (hits.get(key) != null) {
            hits.put(key, hits.get(key) + 1);
        } else {
            hits.put(key, 1);
        }
    }

    /**
     * Increments the number of hits for the key if it's not the first miss. Otherwise inserts it.
     * @param key
     */
    @Override
    public void onMiss(final K key) {
        if (misses.get(key) != null) {
            misses.put(key, misses.get(key) + 1);
        } else {
            misses.put(key, 1);
        }
    }

    /**
     * Increments the number of hits for the key if it's not the first update. Otherwise inserts it.
     * @param key
     */
    @Override
    public void onPut(final K key, final V value) {
        if (updates.get(key) != null) {
            updates.put(key, updates.get(key) + 1);
        } else {
            updates.put(key, 1);
        }
    }
}
