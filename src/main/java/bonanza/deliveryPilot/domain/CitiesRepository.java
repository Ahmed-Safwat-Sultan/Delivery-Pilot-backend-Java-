package bonanza.deliveryPilot.domain;

import java.util.List;

public interface CitiesRepository {

    List<City> saveCities(List<City> cities);

    List<City> getAllCities();

    City getCityById(int cityId);
}
