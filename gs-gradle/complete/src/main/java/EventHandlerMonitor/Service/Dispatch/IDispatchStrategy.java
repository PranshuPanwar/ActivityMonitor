package EventHandlerMonitor.Service.Dispatch;

import EventHandlerMonitor.Dto.DispatcherStrategy;
import EventHandlerMonitor.Enum.DispatchStrategyType;

public interface IDispatchStrategy {
    DispatchStrategyType getDispatchStrategyType();

    void dispatchEvent(String client , String message);
}
