package io.github.u10.fiscobcosstarter.config;

import java.math.BigInteger;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fisco-bcos.gas")
public class GasConfig {

    @Setter
    private String gasPrice;

    @Setter
    private String gasLimit;

    public BigInteger getGasPrice() {
        return new BigInteger(gasPrice);
    }

    public BigInteger getGasLimit() {
        return new BigInteger(gasLimit);
    }
}
