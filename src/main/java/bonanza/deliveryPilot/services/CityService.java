package bonanza.deliveryPilot.services;

import bonanza.deliveryPilot.domain.CitiesRepository;
import bonanza.deliveryPilot.domain.City;
import bonanza.deliveryPilot.domain.Road;
import bonanza.deliveryPilot.mapper.RoadToEdgeMapper;
import bonanza.deliveryPilot.model.WeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    public CitiesRepository citiesRepository;






    public CityService() {

    }

    public List<City> saveCities(List<City> cities){
        return citiesRepository.saveCities(cities);
    }



    public List<City> getAllCities(){
        return citiesRepository.getAllCities();
    }

    public City getCityById(int cityId){
        return citiesRepository.getCityById(cityId);
    }



}
