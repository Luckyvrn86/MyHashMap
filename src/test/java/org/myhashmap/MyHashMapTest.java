package org.myhashmap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    MyHashMap <Integer, String> map = new MyHashMap<>();
    int index = 0;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 100_000; i++) {
            map.put(i, String.valueOf(i));
        }
        index = new Random().nextInt(99999);
    }

    @Test
    void put() {
        map.put(110_000, "1100");
        int size = 100_001;
        assertEquals(size, map.getSize());
    }

    @Test
    void get() {
        assertEquals(String.valueOf(index), map.get(index));
    }

    @Test
    void entrySet() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            list.add(i);
        }
        Collections.sort(list);
        List<Integer> result = map.entrySet().stream()
                .map(Node::getKey)
                .map(k -> Integer.parseInt(String.valueOf(k)))
                .sorted()
                .toList();
        assertArrayEquals(list.toArray(), result.toArray());
    }

    @Test
    void keySet() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100_000; i++) {
            set.add(i);
        }
        assertArrayEquals(set.toArray(), map.keySet().toArray());
    }

    @Test
    void values() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            list.add(String.valueOf(i));
        }
        Collections.sort(list);
        List<String> values = map.values();
        Collections.sort(values);
        assertArrayEquals(list.toArray(), values.toArray());
    }

    @Test
    void remove() {
        map.remove(index);
        assertNull(map.get(index));
    }

    @Test
    void containsKey() {
        assertTrue(map.containsKey(index));
    }

    @Test
    void containsValue() {
        assertTrue(map.containsValue(String.valueOf(index)));
    }

    @Test
    void getSize() {
        assertEquals(100_000, map.getSize());
    }
}