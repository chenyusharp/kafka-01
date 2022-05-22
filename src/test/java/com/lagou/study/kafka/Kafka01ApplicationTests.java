package com.lagou.study.kafka;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
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
        Gson  gson=new Gson();
        Product  product1=new Product();
        product1.setId(100L);
        product1.setName("test1");
        product1.setImgUrl(new ArrayList<>());
        product1.setSpecification("100g/盒");
        product1.setGmtCreated(new Date());
        product1.setGmtModified(new Date());
        product1.setIsDeleted(0);
        product1.setDesc("插入");
        System.out.println(kafkaProducer.sender(String.valueOf(product1.getId()),gson.toJson(product1)));
        product1.setDesc("更新");
        System.out.println(kafkaProducer.sender(String.valueOf(product1.getId()),gson.toJson(product1)));

        Product  product2=new Product();
        product2.setId(101L);
        product2.setName("test2");
        product2.setImgUrl(new ArrayList<>());
        product2.setSpecification("101g/盒");
        product2.setGmtCreated(new Date());
        product2.setGmtModified(new Date());
        product2.setIsDeleted(0);
        product2.setDesc("插入");
        System.out.println(kafkaProducer.sender(String.valueOf(product2.getId()),gson.toJson(product2)));

    }

}
