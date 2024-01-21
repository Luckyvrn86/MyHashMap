package org.myhashmap;

import java.util.*;

/**
 * Кастомная реализация HashMap. Содержит в себе пары ключ - значение.
 * @param <K> - тип ключа
 * @param <V> - тип значения
 */

public class MyHashMap <K, V> {
    /**
     * @value defaultCapacity - размер массива по умолчанию
     */

    private final int defaultCapacity = 16;
    /**
     * @value fullSize - коэффициент размера массива, при котором происходит увеличение размера массива на 50%
     * @see MyHashMap#increaseCapacity()
     */
    private final double fullSize = 0.75;
    /**
     * @value array - массив с парами ключ - значение
     */
    private Node[] array;
    private int size = 0;

    public MyHashMap() {
        array = new Node[defaultCapacity];
    }

    public MyHashMap(int capacity) {
        array = new Node[capacity];
    }

    /**
     * добавление элемента в коллекцию
     */
    public K put (K key, V value) {
        if (size >= array.length * fullSize) increaseCapacity();
        K oldKey = null;
        Node<K, V> node = new Node<>(key, value);
        int index = Math.abs(Objects.hash(key) % array.length);
        node.setIndex(index);
        node.setHash(Objects.hash(key));
        if (array[index] == null) {
            array[index] = node;
            size++;
            return null;
        }
        Node currentNode = array[index];
        if (currentNode.getKey().equals(node.getKey())) {
            oldKey = (K) currentNode.getKey();
            array[index] = node;
        }
        else {
            Node nextNode = currentNode.getNext();
            while (nextNode != null) {
                currentNode = nextNode;
                nextNode = nextNode.getNext();
                if (currentNode.getKey().equals(node.getKey())) {
                    node.setNext(currentNode.getNext());
                    oldKey = (K) currentNode.getKey();
                    currentNode = node;
                    size--;
                    break;
                }
            }
            currentNode.setNext(node);
            size++;
            return oldKey;
        }
        size++;
        return oldKey;
    }

    /**
     * получение элемента из коллекции
     */
    public V get (K key) {
        int index = Math.abs(Objects.hash(key) % array.length);
        if (array[index] == null) return null;
        Node node = array[index];
        while (!node.getKey().equals(key)) {
            node = node.getNext();
        }
        return (V)node.getValue();
    }

    /**
     * получение списка всех элементов коллекции
     */
    public Set<Node> entrySet() {
        Set<Node> set = new HashSet<>();
        Node node;
        for (int i = 0; i < array.length; i++) {
            node = array[i];
            if (node != null) {
                set.add(node);
                while (node.getNext() != null) {
                    node = node.getNext();
                    set.add(node);
                }
            }
        }
        return set;
    }

    /**
     * получение списка ключей коллекции
     */
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        Set<Node> entrySet = entrySet();
        for (Node node : entrySet) {
            set.add((K)(node.getKey()));
        }
        return set;
    }

    /**
     * получение списка значений коллекции
     */
    public List<V> values() {
        List<V> values = new ArrayList<>();
        Set<Node> entrySet = entrySet();
        for (Node node : entrySet) {
            values.add((V)(node.getValue()));
        }
        return values;
    }

    /**
     * удаление элемента из коллекции
     */
    public V remove (K key) {
        V value = null;
        Set<Node> set = this.entrySet();
        Node thisNode = new Node();
        for (Node node : set) {
            if (node.getKey().equals(key)) thisNode = node;
        }
        Node currentNode = null;
        int index = thisNode.getIndex();
        Node tempNode = array[index];
        if (tempNode.getNext() != null) {
            currentNode = tempNode;
            while (!tempNode.getKey().equals(key) && tempNode != null) {
                currentNode = tempNode;
                tempNode = tempNode.getNext();
            }
            currentNode.setNext(tempNode.getNext());
            value = (V)tempNode.getValue();
        } else {
            value = (V)tempNode.getValue();
            array[index] = null;
        }
        return value;
    }
    /**
     * проверка на содержание ключа в коллекции
     */

    public boolean containsKey(K key) {
        Set<K> set = keySet();
        for (K k : set) {
            if (k.equals(key)) return true;
        }
        return false;
    }
    /**
     * проверка на содержание значения в коллекции
     */

    public boolean containsValue(V value) {
        List<V> values = values();
        for (V v : values) {
            if (v.equals(value)) return true;
        }
        return false;
    }
    /**
     * получение размера коллекции
     */

    public int getSize() {
        return size;
    }
    /**
     * увеличение массива для хранения данных внутри коллекции
     */

    private void increaseCapacity() {
        Node[] newArray = new Node[(int) (array.length * 1.5)];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

}
