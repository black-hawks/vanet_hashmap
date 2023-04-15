package dataStructure.graph;

import java.util.List;
/**
 A class representing a value in a graph.
 @param <K> the type of the vertices in the value
 */
public class Route<K> {

    /**
     The distance of the value.
     */
    private int distance;

    /**
     The path of vertices in the value.
     */
    private List<K> path;

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPath(List<K> path) {
        this.path = path;
    }

    /**
     Constructs a new Route object with the given distance and path.
     @param distance the distance of the value
     @param path the path of vertices in the value
     */
    public Route(int distance, List<K> path){
        this.distance = distance;
        this.path = path;
    }

    /**
     Returns the distance of the value.
     @return the distance of the value
     */
    public int getDistance() {
        return distance;
    }

    /**
     Returns the path of vertices in the value.
     @return the path of vertices in the value
     */
    public List<K> getPath() {
        return path;
    }
}