package EventHandlerMonitor.Dto;

import EventHandlerMonitor.Enum.AlertConfigType;
import lombok.Data;

@Data
public class AlertConfig {

    private AlertConfigType type;
    private long count;
    private long windowSizeInSecs;
}
