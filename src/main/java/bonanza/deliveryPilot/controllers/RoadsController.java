package bonanza.deliveryPilot.controllers;

import bonanza.deliveryPilot.domain.Road;
import bonanza.deliveryPilot.services.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoadsController {

    @Autowired
    RoadService roadService;

    @PostMapping("/roads")
    public ResponseEntity addRoads(@RequestBody List<Road> roads){
        roadService.saveRoads(roads);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/roads/shortest-path")
    public List<String> findShortestPath(@RequestParam int startingCity, @RequestParam int endingCity){
        return roadService.findShortestPath(startingCity, endingCity);
    }
}
