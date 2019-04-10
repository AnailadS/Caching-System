package observerPattern.classes;

import java.util.ArrayList;

import observerPattern.interfaces.CacheListener;

/**
 * The BroadcastListener broadcasts cache events to other listeners that have been added to it.
 */
public class BroadcastListener<K, V> implements CacheListener<K, V> {

    private ArrayList<CacheListener<K, V>> listeners = new ArrayList<CacheListener<K, V>>();
    /**
     * Add a listener to the broadcast list.
     *
     * @param listener the listener
     */
    public void addListener(final CacheListener<K, V> listener) {
        listeners.add(listener);
    }

    /**
     * Call the onHit(K key) on every listener.
     */
    @Override
    public void onHit(final K key) {
        for (CacheListener<K, V> i : listeners) {
            i.onHit(key);
        }

    }

    /**
     * Call the onMiss(K key) on every listener.
     */
    @Override
    public void onMiss(final K key) {
        for (CacheListener<K, V> i : listeners) {
            i.onMiss(key);
        }
    }

    /**
     * Call the onPut(K key, V value) on every listener.
     */
    @Override
    public void onPut(final K key, final V value) {
        for (CacheListener<K, V> i : listeners) {
            i.onPut(key, null);
        }
    }
}
