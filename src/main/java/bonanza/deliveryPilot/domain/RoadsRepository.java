package bonanza.deliveryPilot.domain;

import java.util.List;

public interface RoadsRepository {
    List<Road> saveRoads(List<Road> roads);
    public List<Road> getAllRoads();
}
