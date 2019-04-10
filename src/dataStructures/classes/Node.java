package dataStructures.classes;

public class Node<K, V>  {

    private Node<K, V> next;
    private Node<K, V> prev;
    private V value;
    private K key;

    public Node(final K key,  final V value) {
        this.value = value;
        next = null;
        prev = null;
        this.setKey(key);
    }

    public final Node<K, V> getNext() {
        return next;
    }

    public final void setNext(final Node<K, V> next) {
        this.next = next;
    }

    public final Node<K, V> getPrev() {
        return prev;
    }

    public final void setPrev(final Node<K, V> prev) {
        this.prev = prev;
    }

    public final V getValue() {
        return value;
    }

    public final void setValue(final V value) {
        this.value = value;
    }

    public final K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }
   
}
