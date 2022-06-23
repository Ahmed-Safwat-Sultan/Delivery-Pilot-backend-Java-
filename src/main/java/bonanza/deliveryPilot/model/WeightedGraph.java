package bonanza.deliveryPilot.model;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class WeightedGraph<T> implements Graph<T> {

    List<T> vertices = new ArrayList<>();
    List<List<Edge>> adjacentNodesList = new ArrayList<>();


    public WeightedGraph(){

    }

    public WeightedGraph(List<T> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }

        createAdjacentNodesList(edges);
    }

    public void reconstruct(List<T> vertices, List<Edge> edges) {
        clear();
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }

        createAdjacentNodesList(edges);
    }

    public void clear(){
        vertices.clear();
        adjacentNodesList.clear();
    }

    @Override
    public void addVertex(T vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            adjacentNodesList.add(new ArrayList<Edge>());
        } else {
            throw new IllegalArgumentException("This node exists");
        }
    }

    @Override
    public void addEdge(Edge e) {
        if (e.startingVertex < 0 || e.startingVertex > vertices.size() - 1) {
            throw new IllegalArgumentException("wrong index for this edge");
        }
        if (e.endingVertex < 0 || e.endingVertex > vertices.size() - 1) {
            throw new IllegalArgumentException("wrong index for this edge");
        }
        if (!adjacentNodesList.get(e.startingVertex).contains(e)) {
            adjacentNodesList.get(e.startingVertex).add(e);
        } else {
            throw new IllegalArgumentException("this edge already exists");
        }
    }

    @Override
    public int getIndex(T t) {
        return vertices.indexOf(t);
    }

    private void createAdjacentNodesList(List<Edge> edges) {
        for (Edge edge : edges) {
            addEdge(edge);
        }
    }


    public class Tree {
        @Getter
        private int root;
        @Getter
        private int[] parent;
        @Getter
        private double[] cost;
        @Getter
        private List<Integer> pathOrder;

        public Tree(int root, int[] parent, List<Integer> pathOrder, double[] cost) {
            this.root = root;
            this.parent = parent;
            this.pathOrder = pathOrder;
            this.cost = cost;
        }

        public List<T> getPath(int index) {
            ArrayList<T> path = new ArrayList<>();

            do {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);
            return path;
        }

    }

    //abstract public Object shortestPath(int source, int destination);

    public Tree getShortestPath(int sourceIndex) {
        double[] cost = new double[vertices.size()];

        for (int i = 0; i < cost.length; i++) {
            cost[i] = Double.POSITIVE_INFINITY;
        }

        cost[sourceIndex] = 0;

        int[] parent = new int[vertices.size()];
        parent[sourceIndex] = -1;

        List<Integer> touchedNodes = new ArrayList<>();

        while (touchedNodes.size() < vertices.size()) {
            int vertex = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < vertices.size(); i++) {
                if (!touchedNodes.contains(i) && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    vertex = i;
                }
            }
            touchedNodes.add(vertex);

            for (Edge e : adjacentNodesList.get(vertex)) {
                if (!touchedNodes.contains(e.endingVertex) && cost[e.endingVertex] > cost[vertex] + e.weight) {
                    cost[e.endingVertex] = cost[vertex] + e.weight;
                    parent[e.endingVertex] = vertex;

                }
            }
        }

        return new Tree(sourceIndex, parent, touchedNodes, cost);

    }
}
