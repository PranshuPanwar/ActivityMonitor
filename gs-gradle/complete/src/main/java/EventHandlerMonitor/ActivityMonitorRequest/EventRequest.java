package EventHandlerMonitor.ActivityMonitorRequest;

import EventHandlerMonitor.Enum.EventType;
import lombok.Data;

@Data
public class EventRequest {
    private String clientId;
    private EventType eventType;
}
