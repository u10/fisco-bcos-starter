package io.github.u10.fiscobcosstarter.config;

import lombok.Setter;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fisco-bcos.encrypt-type")
public class EncryptTypeConfig {

    @Setter
    private int encryptType;

    @Bean
    public EncryptType getEncryptType() {
        return new EncryptType(encryptType);
    }
}
