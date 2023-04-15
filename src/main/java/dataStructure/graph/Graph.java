package dataStructure.graph;


import dataStructure.hashMap.HashMap;

import java.util.List;

public interface Graph<V> {
    public void addVertex(V vertex);

    public void addEdge(V source, V destination, Integer weight);

    public void removeVertex(V vertex);

    public void removeEdge(V source, V destination);

    public Route<V> shortestPath(V source, V destination);

    public List<V> getVertices();

    public int getNumberOfVertices();
}
