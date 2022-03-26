package pl.sikor.williroi.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="custom")
public class ConfigurationProperties {
    
}
