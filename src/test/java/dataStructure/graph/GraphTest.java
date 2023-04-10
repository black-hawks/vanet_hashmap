package dataStructure.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    private Graph<String, Integer> graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testAddVertex() {
        graph.addVertex("A");
        assertTrue(graph.getAdjacencyMap().containsKey("A"));
    }

    @Test
    public void testAddEdge() {
        graph.addEdge("A", "B", 10);
        assertTrue(graph.getAdjacencyMap().containsKey("A"));
        assertTrue(graph.getAdjacencyMap().get("A").containsKey("B"));
        assertEquals(10, (int) graph.getAdjacencyMap().get("A").get("B"));
    }

    @Test
    public void testRemoveVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 10);
        graph.removeVertex("A");
        assertFalse(graph.getAdjacencyMap().containsKey("A"));
        assertFalse(graph.getAdjacencyMap().get("B").containsKey("A"));
    }

    @Test
    public void testRemoveEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 10);
        graph.removeEdge("A", "B");
        assertFalse(graph.getAdjacencyMap().get("A").containsKey("B"));
    }
}