package io.github.u10.fiscobcosstarter.config;

import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Web3jConfig {

    @Autowired
    private ConnectConfig connectConfig;

    @Bean
    public Web3j getWeb3j(Service service) throws Exception {
        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        service.run();
        channelEthereumService.setChannelService(service);
        channelEthereumService.setTimeout(connectConfig.getTimeout());
        return Web3j.build(channelEthereumService, service.getGroupId());
    }
}
