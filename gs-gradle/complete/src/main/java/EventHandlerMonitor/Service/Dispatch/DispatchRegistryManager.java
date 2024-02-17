package EventHandlerMonitor.Service.Dispatch;

import EventHandlerMonitor.Enum.DispatchStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DispatchRegistryManager {

    private final Map<DispatchStrategyType, IDispatchStrategy> dispatchStrategyMap;

    @Autowired
    public DispatchRegistryManager(List<IDispatchStrategy> programManagerList) {
        this.dispatchStrategyMap = programManagerList.stream()
                .collect(Collectors.toMap(IDispatchStrategy::getDispatchStrategyType, Function.identity()));
    }

    public IDispatchStrategy fetchProgramManager(DispatchStrategyType dispatchStrategyType) throws Exception {
        IDispatchStrategy dispatchStrategy =  dispatchStrategyMap.getOrDefault(dispatchStrategyType, null);
        if (dispatchStrategy == null) {
            throw new Exception("Invalid program Manager");
        }
        return dispatchStrategy;
    }
}
