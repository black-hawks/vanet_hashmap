package dataStructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {

    @Test
    public void testPutAndGet() {
        HashMap<String, Integer> map = new HashMap<>(10);

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
        assertEquals(3, map.get("C"));
    }

    @Test
    public void testRemove() {
        HashMap<String, Integer> map = new HashMap<>(10);

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        map.remove("B");

        Assertions.assertNull(map.get("B"));
        assertEquals(1, map.get("A"));
        assertEquals(3, map.get("C"));
    }

    @Test
    public void testOverwrite() {
        HashMap<String, Integer> map = new HashMap<>(10);

        map.put("A", 1);
        map.put("A", 2);

        assertEquals(2, map.get("A"));
    }

    @Test
    public void testCollisions() {
        HashMap<Integer, String> map = new HashMap<>(5);

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
        map.put(6, "F");

        assertEquals("A", map.get(1));
        assertEquals("B", map.get(2));
        assertEquals("C", map.get(3));
        assertEquals("D", map.get(4));
        assertEquals("E", map.get(5));
        assertEquals("F", map.get(6));
    }

    @Test
    public void testKeysIterator() {
        HashMap<String, Integer> map = new HashMap<>(3);
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
        HashMap<String, Integer> map = new HashMap<>(3);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Test values() iterator
        Iterator<Integer> valuesIterator = map.values();
        assertTrue(valuesIterator.hasNext());
        assertEquals(Integer.valueOf(1), valuesIterator.next());
        assertTrue(valuesIterator.hasNext());
        assertEquals(Integer.valueOf(2), valuesIterator.next());
        assertTrue(valuesIterator.hasNext());
        assertEquals(Integer.valueOf(3), valuesIterator.next());
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
        HashMap<String, Integer> map = new HashMap<>(3);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Test entries() iterator
        Iterator<Entry<String, Integer>> entriesIterator = map.entries();
        assertTrue(entriesIterator.hasNext());
        Entry<String, Integer> entry1 = entriesIterator.next();
        assertEquals("one", entry1.getKey());
        assertEquals(Integer.valueOf(1), entry1.getValue());
        assertTrue(entriesIterator.hasNext());
        Entry<String, Integer> entry2 = entriesIterator.next();
        assertEquals("two", entry2.getKey());
        assertEquals(Integer.valueOf(2), entry2.getValue());
        assertTrue(entriesIterator.hasNext());
        Entry<String, Integer> entry3 = entriesIterator.next();
        assertEquals("three", entry3.getKey());
        assertEquals(Integer.valueOf(3), entry3.getValue());
        assertFalse(entriesIterator.hasNext());
        try {
            entriesIterator.next();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // Test passed
        }
    }
}
