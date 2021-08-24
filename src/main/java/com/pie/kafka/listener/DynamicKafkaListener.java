package com.pie.kafka.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * ClassName DynamicKafkaListener
 * Description
 * Date 2021/8/24
 * Author wangxiyue.xy@163.com
 */
@Component
public class DynamicKafkaListener implements ConsumerAwareMessageListener<String, String> {


    @Override
    public void onMessage(ConsumerRecord<String, String> data, Consumer<?, ?> consumer) {
        System.out.println(data.key() + "===>" + data.value());
        System.out.println(data.topic());
    }
}
