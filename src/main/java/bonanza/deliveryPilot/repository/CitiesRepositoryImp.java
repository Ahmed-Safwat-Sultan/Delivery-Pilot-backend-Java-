package bonanza.deliveryPilot.repository;

import bonanza.deliveryPilot.domain.CitiesRepository;
import bonanza.deliveryPilot.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CitiesRepositoryImp implements CitiesRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<City> saveCities(List<City> cities) {
        List<City> resultList = new ArrayList<>();
        for (City city : cities) {

            if (checkIfCityExists(city.getName())) {
                throw new IllegalArgumentException("City" + city.getName() + "already exists");
            }
            jdbc.update("INSERT INTO CITIES(name) values(?)",
                    city.getName());

            resultList.add(jdbc.queryForObject("SELECT id, name FROM CITIES WHERE name = ?", this::mapToCity, city.getName()));
        }
        return resultList;
    }


    @Override
    public List<City> getAllCities() {
        return jdbc.query("SELECT * FROM CITIES", this::mapToCity);
    }


    @Override
    public City getCityById(int cityId) {
        return jdbc.queryForObject("SELECT * FROM CITIES WHERE id = ?", this::mapToCity, cityId);
    }


    public boolean checkIfCityExists(String name) {
        String sql = "SELECT count(*) FROM CITIES WHERE name = ?";
        boolean exists = false;
        int count = jdbc.queryForObject(sql, Integer.class, name);
        exists = count > 0;
        return exists;

    }

    private City mapToCity(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        City city = new City();
        city.setId(id);
        city.setName(name);
        return city;
    }


}
