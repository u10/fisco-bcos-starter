package io.github.u10.fiscobcosstarter.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.fisco.bcos.channel.handler.ChannelConnections;
import org.fisco.bcos.channel.handler.GroupChannelConnectionsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "fisco-bcos.group-channel-connections-config")
public class GroupChannelConnectionsPropertyConfig {
    @Autowired
    private ConnectConfig connectConfig;

    private List<ChannelConnections> allChannelConnections = new ArrayList<>();

    private String getPath (String path) {
        if (path != null && path.indexOf(":") == -1) {
            path = "file:" + path;
        }
        return path;
    }

    @Bean
    public GroupChannelConnectionsConfig getGroupChannelConnections() {
        String caCertPath = getPath(connectConfig.getCaCertPath());
        String nodeCertPath = getPath(connectConfig.getNodeCertPath());
        String nodeKeyPath = getPath(connectConfig.getNodeKeyPath());

        for (ChannelConnections connections: allChannelConnections) {
            if (caCertPath != null) connections.setCaCertPath(caCertPath);
            if (nodeCertPath != null) connections.setNodeCaPath(nodeCertPath);
            if (nodeKeyPath != null) connections.setNodeKeyPath(nodeKeyPath);
        }

        GroupChannelConnectionsConfig groupChannelConnectionsConfig =
                new GroupChannelConnectionsConfig();
        groupChannelConnectionsConfig.setAllChannelConnections(allChannelConnections);
        return groupChannelConnectionsConfig;
    }
}
