package EventHandlerMonitor.Dto;

import EventHandlerMonitor.Enum.AlertConfigType;
import lombok.Data;

import java.util.List;

@Data
public class AlertConfig {

    private AlertConfigType type;
    private long count;
    private long windowSizeInSecs;

    private List<DispatcherStrategy> dispatcherStrategyList;
}
