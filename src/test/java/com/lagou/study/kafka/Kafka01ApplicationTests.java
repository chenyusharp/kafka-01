package com.lagou.study.kafka;

import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Kafka01ApplicationTests {
    @Resource
    private KafkaProducer kafkaProducer;

    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
        System.out.println(kafkaProducer.sender("Hello Kafka!"));
    }

}
