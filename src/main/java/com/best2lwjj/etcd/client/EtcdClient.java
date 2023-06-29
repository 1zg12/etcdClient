package com.best2lwjj.etcd.client;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.kv.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class EtcdClient {

    final Client client;
    public EtcdClient(Client client) {
        this.client = client;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() throws ExecutionException, InterruptedException {
        KV kvClient = client.getKVClient();
        ByteSequence key = ByteSequence.from("test_key".getBytes());
        ByteSequence value = ByteSequence.from("test_value".getBytes());

// put the key-value
//        kvClient.put(key, value).get();

// get the CompletableFuture
        CompletableFuture<GetResponse> getFuture = kvClient.get(key);

// get the value from CompletableFuture
        GetResponse response = getFuture.get();
        log.info("check the response {}", response);

// delete the key
//        kvClient.delete(key).get();
    }
    @EventListener(ApplicationReadyEvent.class)
    public void watch() throws ExecutionException, InterruptedException {
        Watch kvClient = client.getWatchClient();
        ByteSequence key = ByteSequence.from("commitToSOR".getBytes());

        kvClient.watch(ByteSequence.from("commitToSOR".getBytes()), watchResponse -> log.info("listening to commitToSOR {}", watchResponse));

    }
}
