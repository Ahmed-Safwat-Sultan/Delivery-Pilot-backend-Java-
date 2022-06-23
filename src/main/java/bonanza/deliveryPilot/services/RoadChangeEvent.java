package bonanza.deliveryPilot.services;

import org.springframework.context.ApplicationEvent;

public class RoadChangeEvent extends ApplicationEvent {
    private String message;

    public RoadChangeEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}