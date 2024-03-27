package org.compie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "balldontlie")
@ConfigurationPropertiesScan
@Data
public class BalldontlieConfigurationProperties {
    private String host;
    private String apiKey;
}
