package EventHandlerMonitor.Configurations;

import EventHandlerMonitor.Dto.AlertConfig;
import EventHandlerMonitor.Enum.EventType;
import lombok.Data;

import java.util.List;

@Data
public class ClientConfigurationProperties {
    private String client;
    private EventType eventType;
    private List<AlertConfig> alertConfig;
}
