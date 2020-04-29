package io.github.u10.fiscobcosstarter.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.channel.handler.GroupChannelConnectionsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fisco-bcos.channel-service")
@Slf4j
public class ServiceConfig {

    @Autowired
    private ConnectConfig connectConfig;

    @Getter @Setter
    private String agencyName;

    @Getter @Setter
    private int groupId;

    @Bean
    public Service getService(GroupChannelConnectionsConfig groupChannelConnectionsConfig) {
        Service channelService = new Service();
        channelService.setConnectSeconds(connectConfig.getConnectSeconds());
        channelService.setOrgID(agencyName);
        log.info("agencyName : {}", agencyName);
        channelService.setConnectSleepPerMillis(connectConfig.getConnectSleepPerMillis());
        channelService.setGroupId(groupId);
        channelService.setAllChannelConnections(groupChannelConnectionsConfig);
        return channelService;
    }
}
