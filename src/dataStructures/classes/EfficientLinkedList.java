package dataStructures.classes;

import java.util.HashMap;
/**
 * This class it's an ordered HashMap, that has the advantage of O(1)
 * complexity for search/insert operations, and its entries are ordered.
 *
 * @param <K>
 * @param <V>
 */
public class EfficientLinkedList<K, V> {

    private HashMap<K, Node<K, V>> map = new HashMap<K, Node<K, V>>();
    private K head = null;
    private K tail = null;

    /**
     * Returns the first entry.
     * @return head
     */
    public final K getHead() {
        return head;
    }

    /**
     * Return the last entry.
     * @return tail
     */
    public final K getTail() {
        return tail;
    }

    /**
     * Inserts a new entry.
     * @param key
     * @param value
     */
    public final void put(final K key, final V value) {

        remove(key);

        Node<K, V> node = new Node<K, V>(key, value);
        if (map.size() == 0) {
            head = key;
            tail = key;

        } else {
            map.get(tail).setNext(node);
            node.setPrev(map.get(tail));
            tail = key;
        }
        map.put(key, node);
    }

    /**
     * Returns the Node<K, V> associated with the key.
     * @param key
     * @return Node<K, V>
     */
    public final Node<K, V> get(final K key) {
        return map.get(key);
    }

    /**
     * Removes the entry associated with key.
     * @param key
     * @return Node<K, V> associated with key or null if it doesn't exist in ADT.
     */
    public final Node<K, V> remove(final K key) {

        Node<K, V> node = map.remove(key);
        if (node != null) {
            Node<K, V> next = node.getNext();
            Node<K, V> prev = node.getPrev();
            if (key.equals(head) && key.equals(tail)) {
                head = null;
                tail = null;
            } else if (key.equals(head)) {
                head = next.getKey();
                next.setPrev(null);
            } else if (key.equals(tail)) {
                tail = prev.getKey();
                prev.setNext(null);
            } else {
                next.setPrev(prev);
                prev.setNext(next);
            }
        }
        return node;
    }

    /**
     * @return number of entries
     */
    public final int size() {
        return map.size();
    }

    /**
     * @return @true if there are no entries, @false otherwise.
     */
    public final boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Removes all of the entries.
     */
    public final void clearAll() {
        map.clear();
        head = null;
        tail = null;
    }

}
