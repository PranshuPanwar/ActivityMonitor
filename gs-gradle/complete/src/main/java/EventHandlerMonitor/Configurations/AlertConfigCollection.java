package EventHandlerMonitor.Configurations;

import lombok.Data;

import java.util.List;

@Data
public class AlertConfigCollection {

    private List<ClientConfigurationProperties> alertConfigList;
}
