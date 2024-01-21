package org.myhashmap;

import java.util.Objects;

/**
 * Реализаци контейнера для хранения пары ключ-значение кастомного класса HashMap
 * @see MyHashMap
 * @param <K> - тип ключа
 * @param <V> - тип значения
 */
public class Node <K, V> {

    private int hash;
    private int index;
    private K key;
    private V value;
    private Node<K, V> next = null;
    public Node(){}

    public Node(int hash, K key, V value, Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        hash = Objects.hash(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return hash == node.hash && Objects.equals(key, node.key) && Objects.equals(value, node.value) && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    public int getHash() {
        return hash;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                ", next=" + next +
                '}';
    }
}
