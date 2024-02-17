import java.util.LinkedList;
import java.util.Queue;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

class TumblingWindow {
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

    public boolean processEvent(long eventTimeInSec) {
        if (eventTimeInSec >= currentWindowStart && eventTimeInSec < currentWindowStart + windowInSecs) {
            eventTimestamps.add(eventTimeInSec);
        } else {
            while (eventTimeInSec >= currentWindowStart + windowInSecs) {
                currentWindowStart += windowInSecs;
                eventTimestamps.clear();
            }
            eventTimestamps.add(eventTime);
        }

        System.out.println("Event processed by tumbling window. Current bucket size: " + eventTimestamps.size());

        if( eventTimestamps.size() >= this.threshold){
            return true;
        }
        
        return false;
    }

    public static void main(String[] args) {
        TumblingWindow tumblingWindow = new TumblingWindow(3600,10);
        long now = Instant.now().toEpochMilli();
        tumblingWindow.processEvent(now);        
    }
}
