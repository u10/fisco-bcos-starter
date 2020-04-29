package io.github.u10.fiscobcosstarter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fisco-bcos.connect")
public class ConnectConfig {

    @Getter @Setter
    private int connectSeconds;

    @Getter @Setter
    private int connectSleepPerMillis;

    @Getter @Setter
    private int timeout;

    @Getter @Setter
    private String caCertPath;

    @Getter @Setter
    private String nodeCertPath;

    @Getter @Setter
    private String nodeKeyPath;
}
