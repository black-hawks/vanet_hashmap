package dataStructure.graph;

import dataStructure.graph.hashMapGraph.HashMapGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapGraphTest {
    private HashMapGraph<String> hashMapGraph;

    @BeforeEach
    public void setUp() {
        hashMapGraph = new HashMapGraph<>();
    }

    @Test
    public void testAddVertex() {
        hashMapGraph.addVertex("A");
        assertTrue(hashMapGraph.getAdjacencyMap().containsKey("A"));
    }

    @Test
    public void testAddEdge() {
        hashMapGraph.addEdge("A", "B", 10);
        assertTrue(hashMapGraph.getAdjacencyMap().containsKey("A"));
        assertTrue(hashMapGraph.getAdjacencyMap().get("A").containsKey("B"));
        assertEquals(10, (int) hashMapGraph.getAdjacencyMap().get("A").get("B"));
    }

    @Test
    public void testRemoveVertex() {
        hashMapGraph.addVertex("A");
        hashMapGraph.addVertex("B");
        hashMapGraph.addEdge("A", "B", 10);
        hashMapGraph.removeVertex("A");
        assertFalse(hashMapGraph.getAdjacencyMap().containsKey("A"));
        assertFalse(hashMapGraph.getAdjacencyMap().get("B").containsKey("A"));
    }

    @Test
    public void testRemoveEdge() {
        hashMapGraph.addVertex("A");
        hashMapGraph.addVertex("B");
        hashMapGraph.addEdge("A", "B", 10);
        hashMapGraph.removeEdge("A", "B");
        assertFalse(hashMapGraph.getAdjacencyMap().get("A").containsKey("B"));
    }
}