package io.github.u10.fiscobcosstarter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.u10.fiscobcosstarter.config.AccountConfig;
import io.github.u10.fiscobcosstarter.config.ConnectConfig;
import io.github.u10.fiscobcosstarter.config.EncryptTypeConfig;
import io.github.u10.fiscobcosstarter.config.ServiceConfig;
import io.github.u10.fiscobcosstarter.config.GasConfig;
import io.github.u10.fiscobcosstarter.config.GroupChannelConnectionsPropertyConfig;
import io.github.u10.fiscobcosstarter.config.Web3jConfig;

import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({
  AccountConfig.class,
  EncryptTypeConfig.class,
  GroupChannelConnectionsPropertyConfig.class,
  Web3jConfig.class,
  ConnectConfig.class,
  GasConfig.class,
  ServiceConfig.class
})
public @interface EnableFiscoBcos {
}
