package EventHandlerMonitor.algorithms;

import EventHandlerMonitor.Enum.AlertConfigType;
import EventHandlerMonitor.Enum.EventType;
import EventHandlerMonitor.Service.IEventManager;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.time.Instant;

@Service
public class SimpleCount implements IEventManager {
    private long count;
    private long threshold;

    public SimpleCount(long windowInSecs,long threshold) {
        this.threshold = threshold;
        this.count = 0;
    }

    @Override
    public AlertConfigType getEventManagerType() {
        return AlertConfigType.SIMPLE_COUNT;
    }

    @Override
    public boolean processEvent(long eventTimeInSec) {
        this.count ++;

        if(this.count >= this.threshold){
            return true;
        }

        System.out.println("Event processed by simple count. Current window size: " +this.count);
        return false;
    }

}
