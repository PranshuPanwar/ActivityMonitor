package EventHandlerMonitor.Dto;

import EventHandlerMonitor.Enum.DispatchType;
import lombok.Data;

import java.util.List;

@Data
public class DispatcherStrategy {
    private DispatchType type;
    private String message;
}
