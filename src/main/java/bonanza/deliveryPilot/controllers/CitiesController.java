package bonanza.deliveryPilot.controllers;

import bonanza.deliveryPilot.domain.City;
import bonanza.deliveryPilot.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitiesController {

    @Autowired
    CityService cityService;


    @PostMapping("/cities")
    public ResponseEntity<List<City>> addCities(@RequestBody List<City> cities) {
        List<City> resultList = cityService.saveCities(cities);
        return new ResponseEntity<>(resultList, HttpStatus.CREATED);
    }

    @GetMapping("/cities")
    public List<City> getAllCitiesForDeliveryService(){
        return cityService.getAllCities();
    }


    @GetMapping("/cities/{cityId}")
    public City getCity(@PathVariable int cityId){
        return cityService.getCityById(cityId);
    }
}
