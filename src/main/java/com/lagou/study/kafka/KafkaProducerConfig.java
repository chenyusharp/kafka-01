package com.lagou.study.kafka;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

/**
 * Date: 2022/5/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Component
public class KafkaProducerConfig {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostConstruct
    private void init() {
        DefaultKafkaProducerFactory producerFactory = (DefaultKafkaProducerFactory) kafkaTemplate.getProducerFactory();
        Map<String, Object> configurationMap = new HashMap<>();
        configurationMap.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.lagou.study.kafka.CustomerPartitioner");
        producerFactory.updateConfigs(configurationMap);

    }
}