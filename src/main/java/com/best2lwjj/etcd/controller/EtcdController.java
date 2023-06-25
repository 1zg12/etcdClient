package com.best2lwjj.etcd.controller;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class EtcdController {

    final Client client;
    final KV kvClient;

    public EtcdController(Client client) {
        this.client = client;
        kvClient = client.getKVClient();

    }

    @SneakyThrows
    @GetMapping("/keys")
    public String getKey(@RequestParam String key){
        CompletableFuture<GetResponse> getFuture = kvClient.get(ByteSequence.from(key.getBytes()));

        GetResponse response = getFuture.get();
        log.info("check the response {}", response);
        return  response.toString();
    }
    @SneakyThrows
    @PostMapping ("/keys")
    public String setKey(@RequestParam String key, @RequestParam String value){
        CompletableFuture<GetResponse> getFuture = kvClient.get(ByteSequence.from(key.getBytes()));

        GetResponse response = getFuture.get();
        log.info("check the response {}", response);
        return  response.toString();
    }
}
