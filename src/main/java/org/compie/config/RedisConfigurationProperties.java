package org.compie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "spring")
@ConfigurationPropertiesScan
@Data
public class RedisConfigurationProperties {

    private Redis redis;

    @Data
    public static class Redis {
        private String database;
        private String host;
        private int port;
        private String password;
        private long timeout;
    }
}
