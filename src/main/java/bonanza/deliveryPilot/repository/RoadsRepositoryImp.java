package bonanza.deliveryPilot.repository;

import bonanza.deliveryPilot.domain.Road;
import bonanza.deliveryPilot.domain.RoadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoadsRepositoryImp implements RoadsRepository {

    @Autowired
    JdbcTemplate jdbc;
    @Override
    public List<Road> saveRoads(List<Road> roads) {
        for(Road r: roads) {
            if (checkIfRoadExists(r)) {
                throw new IllegalArgumentException("This road of " + r.getStartingCity() + ", "
                        + r.getEndingCity() + " already exists");
            }
        }
        for(Road r: roads) {
            jdbc.update("INSERT INTO ROADS(cityId1, cityId2, distance) values(?, ?, ?)",
                    r.getStartingCity(), r.getEndingCity(), r.getDistance());
        }

        return roads;
    }
    public boolean checkIfRoadExists(Road road){
        String sql = "SELECT count(*) FROM ROADS WHERE cityId1 = ? and cityId2 = ?";
        boolean exists = false;
        int count = jdbc.queryForObject(sql, Integer.class, road.getStartingCity(), road.getEndingCity());
        exists = count > 0;
        return exists;
    }

    @Override
    public List<Road> getAllRoads() {
        return jdbc.query("SELECT * FROM ROADS", this::mapToRoad);
    }



    private Road mapToRoad(ResultSet rs, int rowNum) throws SQLException {
        int id1 = rs.getInt("CITYID1");
        int id2 = rs.getInt("CITYID2");
        double distance = rs.getDouble("DISTANCE");

        Road edge = new Road();
        edge.setStartingCity(id1);
        edge.setEndingCity(id2);
        edge.setDistance(distance);
        return edge;
    }
}
