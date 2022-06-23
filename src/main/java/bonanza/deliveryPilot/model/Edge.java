package bonanza.deliveryPilot.model;

import lombok.Data;

@Data
public class Edge {
    public int startingVertex;
    public int endingVertex;
    public double weight;

    public Edge(){}
    public Edge(int u, int v, double w) {
        this.startingVertex = u;
        this.endingVertex = v;
        this.weight = w;
    }

}
