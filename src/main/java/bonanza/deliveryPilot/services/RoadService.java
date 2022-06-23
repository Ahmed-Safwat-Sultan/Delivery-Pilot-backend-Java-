package bonanza.deliveryPilot.services;

import bonanza.deliveryPilot.domain.CitiesRepository;
import bonanza.deliveryPilot.domain.City;
import bonanza.deliveryPilot.domain.Road;
import bonanza.deliveryPilot.domain.RoadsRepository;
import bonanza.deliveryPilot.mapper.RoadToEdgeMapper;
import bonanza.deliveryPilot.model.WeightedGraph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class RoadService {

    @Autowired
    RoadEventPublisher roadEventPublisher;

    @Autowired
    RoadsRepository roadsRepository;
    @Autowired
    CitiesRepository citiesRepository;

    @Autowired
    WeightedGraph<String> graph;

    public List<Road> getAllRoads(){
        return roadsRepository.getAllRoads();
    }

    public List<Road> saveRoads(List<Road> roads){
        List<Road> resultList =  roadsRepository.saveRoads(roads);
        roadEventPublisher.publishRoadEvent("Roads have been changed");
        return resultList;
    }

    public void initiateGraph(){
        List<City> cities = citiesRepository.getAllCities();
        List<String> cityNames = new ArrayList<>();
        for(City city: cities){
            cityNames.add(city.getName());
        }
        List<Road> roads = getAllRoads();
        for(Road r: roads){
            r.setStartingCity(r.getStartingCity() - 1);
            r.setEndingCity(r.getEndingCity() - 1);
        }

        graph.reconstruct(cityNames, RoadToEdgeMapper.roadListToEdgeList(roads));
        log.info("graph is reconstructed");
    }

    public List<String> getPath(WeightedGraph graph, int id1, int id2){
        WeightedGraph.Tree tree = graph.getShortestPath(id1 - 1);
        List<String> path = tree.getPath(id2 - 1);
        return path;
    }

    public List<String> findShortestPath(int id1, int id2){
        return getPath(graph, id1, id2);
    }
}
