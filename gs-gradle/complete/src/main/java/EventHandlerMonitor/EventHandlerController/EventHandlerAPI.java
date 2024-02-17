package EventHandlerMonitor.EventHandlerController;

import EventHandlerMonitor.ActivityMonitorRequest.EventRequest;
import EventHandlerMonitor.Service.EventHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deal")
public class EventHandlerAPI {

    @Autowired
    private EventHandlerService eventHandlerService;
    /*
     * API to trigger the events
     *
     */
    @PostMapping("trigger/event")
    public void createDeal(@RequestBody EventRequest request) {

    }
}
