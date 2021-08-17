package com.pie.kafka.listener;

import com.pie.kafka.config.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @wangxiyue.xy@163.com
 * @Date 2021/8/18
 * @Author 虫子
 * @ClassMame: KafkaMessage
 * @Description:
 */
@Component
public class KafkaMessage {
    Logger log =  LoggerFactory.getLogger(KafkaMessage.class);

    @KafkaListener(topics = KafkaConfig.topicName)
    public void onMessage(ConsumerRecord<?,?> record){
        log.info("Kafka消息到达 {} -> {} - {}",record.topic(),record.partition(),record.value());
    }
}
