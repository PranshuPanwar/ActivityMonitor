package EventHandlerMonitor.ClientConfiguration;


import EventHandlerMonitor.Configurations.AlertConfigCollection;
import EventHandlerMonitor.Configurations.ClientConfigurationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;


@Configuration
public class ClientConfigurationParser {
    @Value("classpath:clientConfiguration.json")
    private Resource clientConfigResource;

    @Bean
    public AlertConfigCollection clientConfigProperties(ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(clientConfigResource.getInputStream(), AlertConfigCollection.class);
    }
}
