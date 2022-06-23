package bonanza.deliveryPilot.domain;

import lombok.Data;

@Data
public class Road {
    int startingCity;
    int endingCity;
    double distance;
}
