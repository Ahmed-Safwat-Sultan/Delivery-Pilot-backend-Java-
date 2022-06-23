package bonanza.deliveryPilot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoadChangeEventListener implements ApplicationListener<RoadChangeEvent> {

    @Autowired
    RoadService roadService;

         @Override
        public void onApplicationEvent(RoadChangeEvent event) {
            log.info("Received roadChange Event - " + event.getMessage());
            roadService.initiateGraph();
        }
    }
