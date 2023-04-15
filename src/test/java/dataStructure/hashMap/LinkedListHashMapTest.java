package dataStructure.hashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListHashMapTest {

    @Test
    void testPutAndGet() {
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
    void testRemove() {
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
    void testOverwrite() {
        HashMap<String, Integer> map = new LinkedListHashMap<>();

        map.put("A", 1);
        map.put("A", 2);

        assertEquals(2, map.get("A"));
        assertEquals(1, map.size());
    }

    @Test
    void testCollisions() {
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
    void testKeysIterator() {
        HashMap<String, Integer> map = new LinkedListHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        List<String> expectedKeys = Arrays.asList("cherry", "apple", "banana");
        assertEquals(expectedKeys, map.keys());

        map.put("date", 4);
        expectedKeys = Arrays.asList("cherry", "apple", "banana", "date");
        assertEquals(expectedKeys, map.keys());

        map.remove("banana");
        expectedKeys = Arrays.asList("cherry", "apple", "date");
        assertEquals(expectedKeys, map.keys());
    }

    @Test
    void testValuesIterator() {
        HashMap<String, Integer> map = new LinkedListHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        List<Integer> expectedValues = Arrays.asList(3, 1, 2);
        assertEquals(expectedValues, map.values());

        map.put("date", 4);
        expectedValues = Arrays.asList(3, 1, 2, 4);
        assertEquals(expectedValues, map.values());

        map.remove("banana");
        expectedValues = Arrays.asList(3, 1, 4);
        assertEquals(expectedValues, map.values());
    }

    @Test
    void testEntriesIterator() {
        HashMap<String, Integer> map = new LinkedListHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        // Call the entries() method to get a list of Map.Entry objects
        List<Entry<String, Integer>> entryList = map.entries();

        // Assert that the list is not null and has the correct size
        assertNotNull(entryList);
        assertEquals(3, entryList.size());

        // Assert that each Map.Entry in the list has the correct key-value pair
        for (Entry<String, Integer> entry : entryList) {
            String key = entry.getKey();
            int value = entry.getValue();
            assertTrue(map.containsKey(key));
            assertEquals(map.get(key), value);
        }
    }
}
