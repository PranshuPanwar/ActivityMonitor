package EventHandlerMonitor.Service.Dispatch;

import EventHandlerMonitor.Enum.DispatchStrategyType;
import EventHandlerMonitor.Service.Dispatch.IDispatchStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsoleDispatchStrategy implements IDispatchStrategy {
    @Override
    public DispatchStrategyType getDispatchStrategyType() {
        return DispatchStrategyType.CONSOLE;
    }

    @Override
    public void dispatchEvent(String client, String message) {
        log.info("AlertingService: Dispatching to Console");
        log.warn("Alert : {}", message);
    }
}
