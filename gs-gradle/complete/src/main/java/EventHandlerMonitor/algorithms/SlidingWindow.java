package EventHandlerMonitor.algorithms;

import EventHandlerMonitor.Enum.AlertConfigType;
import EventHandlerMonitor.Enum.EventType;
import EventHandlerMonitor.Service.IEventManager;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.time.Instant;

@Service
public class SlidingWindow implements IEventManager {
    private final long windowInSecs;
    private final Queue<Long> eventTimestamps = new LinkedList<>();
    private long threshold;

    public SlidingWindow(long windowInSecs,long threshold) {
        this.windowInSecs = windowInSecs;
        this.threshold = threshold;
    }

    @Override
    public AlertConfigType getEventManagerType() {
        return AlertConfigType.SLIDING_WINDOW;
    }

    @Override
    public boolean processEvent(long eventTimeInSec) {
        long currentTimeInSec = Instant.now().getEpochSecond();

        while (!eventTimestamps.isEmpty() && currentTimeInSec - eventTimestamps.peek() > windowInSecs) {
            eventTimestamps.poll();
        }

        eventTimestamps.add(eventTimeInSec);

        if (eventTimestamps.size() > this.threshold){
            return true;
        }

        System.out.println("Event processed by sliding window. Current window size: " + eventTimestamps.size());
        return false;
    }

}
