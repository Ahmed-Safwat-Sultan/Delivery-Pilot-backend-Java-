package bonanza.deliveryPilot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoadEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishRoadEvent(final String message) {
        log.info("Publishing custom event. ");
        RoadChangeEvent customSpringEvent = new RoadChangeEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
