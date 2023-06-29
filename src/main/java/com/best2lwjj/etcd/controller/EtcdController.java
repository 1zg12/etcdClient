package com.best2lwjj.etcd.controller;

import com.best2lwjj.etcd.domain.EtcdPair;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequestMapping("/etcd")
public class EtcdController {

    final Client client;
    final KV kvClient;

    public EtcdController(Client client) {
        this.client = client;
        kvClient = client.getKVClient();

    }

    @SneakyThrows
    @GetMapping("/keys/{key}")
    public String getKey(@PathVariable String key){
        CompletableFuture<GetResponse> getFuture = kvClient.get(ByteSequence.from(key.getBytes()));

        GetResponse response = getFuture.get();
//        log.info("check the response {}", response);
        return  response.toString();
    }
    @SneakyThrows
    @PostMapping ("/keys")
    public String setKey(@RequestBody EtcdPair pair){

        ByteSequence key = ByteSequence.from(pair.key().getBytes());
        ByteSequence value = ByteSequence.from(pair.value().getBytes());

        kvClient.put(key, value).get();
        CompletableFuture<GetResponse> getFuture = kvClient.get(ByteSequence.from(pair.key().getBytes()));

        GetResponse response = getFuture.get();
        return  response.toString();
    }
}
