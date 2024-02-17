package EventHandlerMonitor.Dto;

import EventHandlerMonitor.Enum.DispatchStrategyType;
import lombok.Data;

@Data
public class DispatcherStrategy {
    private DispatchStrategyType type;
    private String message;
}
