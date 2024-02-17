package EventHandlerMonitor.Service;

import EventHandlerMonitor.Enum.AlertConfigType;
import EventHandlerMonitor.Enum.EventType;

public interface IEventManager {
    AlertConfigType getEventManagerType();
    boolean processEvent(long eventTimeInSec);
}
