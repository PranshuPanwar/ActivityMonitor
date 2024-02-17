import java.util.LinkedList;
import java.util.Queue;
import java.time.Instant;

class SlidingWindow {
    private final long windowInSecs;
    private final Queue<Long> eventTimestamps = new LinkedList<>();
    private long threshold;

    public SlidingWindow(long windowInSecs,long threshold) {
        this.windowInSecs = windowInSecs;
        this.threshold = threshold;
    }

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

    public static void main(String[] args) {
        SlidingWindow sw = new SlidingWindow(3600,10);
        long now = Instant.now().toEpochMilli();
        sw.processEvent(now);        
    }
}
