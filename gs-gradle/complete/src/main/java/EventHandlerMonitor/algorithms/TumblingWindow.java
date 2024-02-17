package EventHandlerMonitor.algorithms;

import EventHandlerMonitor.Enum.AlertConfigType;
import EventHandlerMonitor.Enum.EventType;
import EventHandlerMonitor.Service.IEventManager;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
class TumblingWindow implements IEventManager {
    private final long windowInSecs;
    private final Queue<Long> eventTimestamps = new LinkedList<>();
    private long currentWindowStart;
    private long threshold;

    public TumblingWindow(long windowInSecs, long threshold) {
        this.windowInSecs = windowInSecs; 
        this.threshold = threshold;
        
        ZonedDateTime startOfDay = ZonedDateTime
                            .now(ZoneId.systemDefault())
                            .toLocalDate().atStartOfDay(ZoneId.systemDefault());
                            
        this.currentWindowStart = startOfDay.toInstant().toEpochMilli();
    }

    @Override
    public AlertConfigType getEventManagerType() {
        return AlertConfigType.TUMBLING_WINDOW;
    }

    @Override
    public boolean processEvent(long eventTimeInSec) {
        if (eventTimeInSec >= currentWindowStart && eventTimeInSec < currentWindowStart + windowInSecs) {
            eventTimestamps.add(eventTimeInSec);
        } else {
            while (eventTimeInSec >= currentWindowStart + windowInSecs) {
                currentWindowStart += windowInSecs;
                eventTimestamps.clear();
            }
            eventTimestamps.add(eventTimeInSec);
        }

        System.out.println("Event processed by tumbling window. Current bucket size: " + eventTimestamps.size());

        if( eventTimestamps.size() >= this.threshold){
            return true;
        }
        
        return false;
    }

}
