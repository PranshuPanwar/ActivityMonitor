import java.util.LinkedList;
import java.util.Queue;
import java.time.Instant;

class SimpleCount {
    private long count;
    private long threshold;

    public SimpleCount(long threshold) {
        this.threshold = threshold;
        this.count = 0;
    }

    public boolean processEvent(long eventTimeInSec) {
        this.count ++;

        if(this.count >= this.threshold){
            return true;
        }

        System.out.println("Event processed by simple count. Current window size: " + eventTimestamps.size());
        return false;
    }

    public static void main(String[] args) {
        SimpleCount sc = new SimpleCount(3600);
        long now = Instant.now().getEpochSecond();
        sc.processEvent(now);
    }
}
