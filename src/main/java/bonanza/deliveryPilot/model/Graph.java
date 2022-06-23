package bonanza.deliveryPilot.model;


import java.util.List;

public interface Graph<T> {

    public void addVertex(T vertex);

    public void addEdge(Edge e);

    public int getIndex(T t);

    public void clear();

    public void reconstruct(List<T> vertices, List<Edge> edges);



}
