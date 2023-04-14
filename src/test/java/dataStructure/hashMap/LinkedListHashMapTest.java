package dataStructure.hashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListHashMapTest {

    @Test
    public void testPutAndGet() {
        HashMap<String, Integer> map = new LinkedListHashMap<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        assertEquals(3, map.size());
        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
        assertEquals(3, map.get("C"));
    }

    @Test
    public void testRemove() {
        HashMap<String, Integer> map = new LinkedListHashMap<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        map.remove("B");
        assertEquals(2, map.size());
        Assertions.assertNull(map.get("B"));
        assertEquals(1, map.get("A"));
        assertEquals(3, map.get("C"));
    }

    @Test
    public void testOverwrite() {
        HashMap<String, Integer> map = new LinkedListHashMap<>();

        map.put("A", 1);
        map.put("A", 2);

        assertEquals(2, map.get("A"));
        assertEquals(1, map.size());
    }

    @Test
    public void testCollisions() {
        HashMap<Integer, String> map = new LinkedListHashMap<>();

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
        map.put(6, "F");

        assertEquals(6, map.size());
        assertEquals("A", map.get(1));
        assertEquals("B", map.get(2));
        assertEquals("C", map.get(3));
        assertEquals("D", map.get(4));
        assertEquals("E", map.get(5));
        assertEquals("F", map.get(6));
    }

    @Test
    void testResize() {
        HashMap<String, Integer> map = new LinkedListHashMap<>(4);
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("date", 4);
        map.put("elderberry", 5);
        assertEquals(5, map.size());
        assertEquals(1, map.get("apple"));
        assertEquals(2, map.get("banana"));
        assertEquals(3, map.get("cherry"));
        assertEquals(4, map.get("date"));
        assertEquals(5, map.get("elderberry"));
    }

    @Test
    public void testKeysIterator() {
        LinkedListHashMap<String, Integer> map = new LinkedListHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Test keys() iterator
        Iterator<String> keysIterator = map.keys();
        assertTrue(keysIterator.hasNext());
        assertEquals("one", keysIterator.next());
        assertTrue(keysIterator.hasNext());
        assertEquals("two", keysIterator.next());
        assertTrue(keysIterator.hasNext());
        assertEquals("three", keysIterator.next());
        assertFalse(keysIterator.hasNext());
        try {
            keysIterator.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // Test passed
        }
    }

    @Test
    public void testValuesIterator() {
        LinkedListHashMap<String, Integer> map = new LinkedListHashMap<>(3);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Test values() iterator
        Iterator<Integer> valuesIterator = map.values();
        assertTrue(valuesIterator.hasNext());
        assertEquals(Integer.valueOf(3), valuesIterator.next());
        assertTrue(valuesIterator.hasNext());
        assertEquals(Integer.valueOf(1), valuesIterator.next());
        assertTrue(valuesIterator.hasNext());
        assertEquals(Integer.valueOf(2), valuesIterator.next());
        assertFalse(valuesIterator.hasNext());
        try {
            valuesIterator.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // Test passed
        }
    }

    @Test
    public void testEntriesIterator() {
        LinkedListHashMap<String, Integer> map = new LinkedListHashMap<>(3);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Test entries() iterator
        Iterator<Entry<String, Integer>> entriesIterator = map.entries();
        assertTrue(entriesIterator.hasNext());
        Entry<String, Integer> entry1 = entriesIterator.next();
        assertEquals("three", entry1.getKey());
        assertEquals(Integer.valueOf(3), entry1.getValue());
        assertTrue(entriesIterator.hasNext());
        Entry<String, Integer> entry2 = entriesIterator.next();
        assertEquals("one", entry2.getKey());
        assertEquals(Integer.valueOf(1), entry2.getValue());
        assertTrue(entriesIterator.hasNext());
        Entry<String, Integer> entry3 = entriesIterator.next();
        assertEquals("two", entry3.getKey());
        assertEquals(Integer.valueOf(2), entry3.getValue());
        assertFalse(entriesIterator.hasNext());
        try {
            entriesIterator.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // Test passed
        }
    }
}
