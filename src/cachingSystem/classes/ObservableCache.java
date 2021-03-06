package cachingSystem.classes;

import cachingSystem.interfaces.Cache;
import cachingSystem.interfaces.CacheStalePolicy;
import dataStructures.classes.Pair;
import observerPattern.interfaces.CacheListener;

/**
 * Abstract class that adds support for listeners and stale element policies to the Cache
 * interface.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class ObservableCache<K, V> implements Cache<K, V> {

    /**
     * Set a policy for removing stale elements from the cache.
     *
     * @param stalePolicy
     */

    protected CacheStalePolicy<K, V> stalePolicy;
    protected CacheListener<K, V> cacheListener;

    /**
     * Set a stale policy for the cache.
     *
     * @param stalePolicy
     */
    public void setStalePolicy(final CacheStalePolicy<K, V> stalePolicy) {
        this.stalePolicy = stalePolicy;
    }

    /**
     * Set a listener for the cache.
     *
     * @param cacheListener
     */
    public void setCacheListener(final CacheListener<K, V> cacheListener) {
        this.cacheListener = cacheListener;
    }

    /**
     * Clear the stale elements from the cache. This method must make use of the stale policy.
     *
     */
    public void clearStaleEntries() {
        Pair<K, V> eldest = getEldestEntry();
        while (eldest != null && stalePolicy.shouldRemoveEldestEntry(eldest)) {
            this.remove(eldest.getKey());
            eldest = getEldestEntry();
        }
    }
}
