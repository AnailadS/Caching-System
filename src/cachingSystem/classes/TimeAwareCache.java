package cachingSystem.classes;

import java.sql.Timestamp;
import java.util.HashMap;

import cachingSystem.interfaces.CacheStalePolicy;
import dataStructures.classes.Pair;

/**
 * The TimeAwareCache offers the same functionality as the LRUCache, but also stores a timestamp for
 * each element. The timestamp is updated after each get / put operation for a key. This
 * functionality allows for time based cache stale policies (e.g. removing entries that are older
 * than 1 second).
 */
public class TimeAwareCache<K, V> extends LRUCache<K, V> {

    private HashMap<K, Timestamp> timestamps = new HashMap<K, Timestamp>();

    /**
     * Get the timestamp associated with a key, or null if the key is not stored in the cache.
     *
     * @param key the key
     * @return the timestamp, or null
     */
    public Timestamp getTimestampOfKey(final K key) {
        return timestamps.get(key);
    }

    /**
     * Set a cache stale policy that should remove all elements older than @millisToExpire
     * milliseconds. This is a convenience method for setting a time based policy for the cache.
     *
     * @param millisToExpire the expiration time, in milliseconds
     */
    public void setExpirePolicy(final long millisToExpire) {
        setStalePolicy(new CacheStalePolicy<K, V>() {
            @Override
            public boolean shouldRemoveEldestEntry(final Pair<K, V> entry) {
                long keyBirth = getTimestampOfKey(entry.getKey()).getTime();
                long current = new Timestamp(System.currentTimeMillis()).getTime();
                if (current - keyBirth > millisToExpire) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Insert a new entry in the cache, and set a timestamp for it.
     * @param key, value
     */
    @Override
    public void put(final K key, final V value) {
        clearStaleEntries();
        timestamps.put(key, new Timestamp(System.currentTimeMillis()));
        super.put(key, value);
    }

    /**
     * Get the value associated with a key, or null if the key does not exist in the cache.
     * @param key
     * @return value associated with a key
     */
    @Override
    public V get(final K key) {
        clearStaleEntries();
        return super.get(key);
    }
}
