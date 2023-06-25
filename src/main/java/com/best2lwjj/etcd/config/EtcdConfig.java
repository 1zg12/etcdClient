package com.best2lwjj.etcd.config;

import io.etcd.jetcd.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EtcdConfig {
    Client client;
    @Bean
    public Client buildClient(){
        client = Client.builder().target("ip:///localhost:2379").build();
        return client;
    }
}
