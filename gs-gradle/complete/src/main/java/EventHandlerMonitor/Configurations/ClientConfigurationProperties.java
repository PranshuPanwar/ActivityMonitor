package EventHandlerMonitor.Configurations;

import EventHandlerMonitor.Dto.AlertConfig;
import EventHandlerMonitor.Enum.EventType;
import lombok.Data;

@Data
public class ClientConfigurationProperties {
    private String client;
    private EventType eventType;
    private AlertConfig alertConfig;
}
