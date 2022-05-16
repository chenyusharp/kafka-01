package com.lagou.study.kafka;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Date: 2022/5/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ZkTest {


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        /**
         * 超时时间
         */
        final int SESSION_TIME_OUT = 2000;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zookeeper = new ZooKeeper("47.95.2.76:2181", SESSION_TIME_OUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Watch received event");
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        System.out.println("zookeeper connection success");

        List<String> children = zookeeper.getChildren("/", false);
        System.out.println(children);
    }

}