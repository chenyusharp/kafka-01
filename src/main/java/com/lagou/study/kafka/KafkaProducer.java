package com.lagou.study.kafka;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.common.PartitionInfo;
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
        final List<PartitionInfo> partitionInfos = kafkaTemplate.partitionsFor(KafkaConstant.TOPIC);
        System.out.println("partitionInfos size:"+partitionInfos.size());
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
