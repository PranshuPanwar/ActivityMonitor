package EventHandlerMonitor.Service;

import EventHandlerMonitor.Enum.AlertConfigType;
import EventHandlerMonitor.Enum.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EventManagerRegistry {
    private final Map<AlertConfigType, IEventManager> eventManagerMap;

    @Autowired
    public EventManagerRegistry(List<IEventManager> programManagerList) {
        this.eventManagerMap = programManagerList.stream()
                .collect(Collectors.toMap(IEventManager::getEventManagerType, Function.identity()));
    }

    public IEventManager fetchProgramManager(AlertConfigType alertConfigType) throws Exception {
        IEventManager eventManager =  eventManagerMap.getOrDefault(alertConfigType, null);
        if (eventManager == null) {
            throw new Exception("Invalid program Manager");
        }
        return eventManager;
    }

}
