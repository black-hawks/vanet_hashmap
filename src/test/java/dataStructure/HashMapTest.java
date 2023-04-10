package dataStructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashMapTest {

    @Test
    public void testPutAndGet() {
        HashMap<String, Integer> map = new HashMap<>(10);

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        Assertions.assertEquals(1, map.get("A"));
        Assertions.assertEquals(2, map.get("B"));
        Assertions.assertEquals(3, map.get("C"));
    }

    @Test
    public void testRemove() {
        HashMap<String, Integer> map = new HashMap<>(10);

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        map.remove("B");

        Assertions.assertNull(map.get("B"));
        Assertions.assertEquals(1, map.get("A"));
        Assertions.assertEquals(3, map.get("C"));
    }

    @Test
    public void testOverwrite() {
        HashMap<String, Integer> map = new HashMap<>(10);

        map.put("A", 1);
        map.put("A", 2);

        Assertions.assertEquals(2, map.get("A"));
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

        Assertions.assertEquals("A", map.get(1));
        Assertions.assertEquals("B", map.get(2));
        Assertions.assertEquals("C", map.get(3));
        Assertions.assertEquals("D", map.get(4));
        Assertions.assertEquals("E", map.get(5));
        Assertions.assertEquals("F", map.get(6));
    }
}
