package com.lagou.study.kafka;

import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public String sender(String key, String content) throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate
                .send(KafkaConstant.TOPIC, key, content);
        if (null != future.get().getRecordMetadata()) {
            System.out.println("消费发送成功 offset:" + future.get().getRecordMetadata().offset());
            return "success";
        } else {
            System.out.println("消费发送失败");
            return "false";
        }
    }
}
