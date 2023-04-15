package dataStructure.graph;


import dataStructure.hashMap.HashMap;

import java.util.List;

/**
 * This interface represents a Graph data structure with vertices and weighted edges.
 *
 * @param <V> the type of the vertices in the Graph
 */
public interface Graph<V> {

    /**
     * Adds a vertex to the Graph.
     *
     * @param vertex the vertex to be added
     */
    void addVertex(V vertex);

    /**
     * Adds an edge to the Graph with the specified source, destination, and weight.
     *
     * @param source the source vertex of the edge
     * @param destination the destination vertex of the edge
     * @param weight the weight of the edge
     */
    void addEdge(V source, V destination, Integer weight);

    /**
     * Removes a vertex from the Graph and all its associated edges.
     *
     * @param vertex the vertex to be removed
     */
    void removeVertex(V vertex);

    /**
     * Removes an edge from the Graph with the specified source and destination vertices.
     *
     * @param source the source vertex of the edge
     * @param destination the destination vertex of the edge
     */
    void removeEdge(V source, V destination);

    /**
     * Finds the shortest path between two vertices in the Graph using Dijkstra's algorithm.
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return a Route object representing the shortest path between the source and destination vertices
     */
    Route<V> shortestPath(V source, V destination);

    /**
     * Gets a List of all the vertices in the Graph.
     *
     * @return a List of all the vertices in the Graph
     */
    List<V> getVertices();

    /**
     * Gets the number of vertices in the Graph.
     *
     * @return the number of vertices in the Graph
     */
    int getNumberOfVertices();
}
