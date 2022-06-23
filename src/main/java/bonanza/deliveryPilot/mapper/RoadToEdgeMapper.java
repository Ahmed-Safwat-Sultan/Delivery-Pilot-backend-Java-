package bonanza.deliveryPilot.mapper;

import bonanza.deliveryPilot.domain.Road;
import bonanza.deliveryPilot.model.Edge;

import java.util.ArrayList;
import java.util.List;

public class RoadToEdgeMapper {

    public static Edge convertRoadToEdge(Road road){
        Edge edge = new Edge(road.getStartingCity(), road.getEndingCity(), road.getDistance());
        return edge;
    }

    public static List<Edge> roadListToEdgeList(List<Road> roads){
        List<Edge> edges = new ArrayList<>();
        for(Road r: roads){
            edges.add(convertRoadToEdge(r));
        }
        return edges;
    }
}
