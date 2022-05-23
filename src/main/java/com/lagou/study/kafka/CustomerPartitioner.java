package com.lagou.study.kafka;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;
import org.springframework.stereotype.Component;

/**
 * Date: 2022/5/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Component
@Slf4j
public class CustomerPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (keyBytes == null) {
            return new DefaultPartitioner().partition(topic, key, keyBytes, value, valueBytes, cluster);
        }
        List<PartitionInfo> availablePartitionList = cluster.availablePartitionsForTopic(topic);
        int partition = Utils.toPositive(Utils.murmur2(keyBytes)) % availablePartitionList.size();
        log.info("partition:{}",partition);
        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}