package EventHandlerMonitor.Service;

import EventHandlerMonitor.ActivityMonitorRequest.EventRequest;
import EventHandlerMonitor.ClientConfiguration.ClientConfigurationParser;
import EventHandlerMonitor.Configurations.AlertConfigCollection;
import EventHandlerMonitor.Configurations.ClientConfigurationProperties;
import EventHandlerMonitor.Dto.AlertConfig;
import EventHandlerMonitor.Dto.DispatcherStrategy;
import EventHandlerMonitor.Service.Dispatch.DispatchRegistryManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class EventHandlerService {

    @Autowired
    private EventManagerRegistry eventManagerRegistry;
    @Autowired
    private ClientConfigurationParser clientConfigurationParser;

    @Autowired
    private DispatchRegistryManager dispatchRegistryManager;

    public void processEvent(EventRequest eventRequest) {
        AlertConfigCollection alertConfigCollection = clientConfigurationParser.clientConfigProperties(new ObjectMapper());

        for (ClientConfigurationProperties clientConfigurationProperties : alertConfigCollection.getAlertConfigList()) {
            if (!clientConfigurationProperties.getEventType().equals(eventRequest.getEventType())) {
                continue;
            }
            List<AlertConfig> alertConfigList = clientConfigurationProperties.getAlertConfig();
            for (AlertConfig alertConfig : alertConfigList) {
                try {
                    boolean doDispatch = eventManagerRegistry.fetchProgramManager(alertConfig.getType()).processEvent(Instant.now().getEpochSecond());
                    if (doDispatch){
                        for(DispatcherStrategy strategy:  alertConfig.getDispatcherStrategyList()){
                           dispatchRegistryManager.fetchProgramManager(strategy.getType()).dispatchEvent(eventRequest.getClientId(), strategy.getMessage());
                        }
                    }
                } catch (Exception e) {
                    log.error("Failed to Process Event");
                }
            }

        }

    }
}
