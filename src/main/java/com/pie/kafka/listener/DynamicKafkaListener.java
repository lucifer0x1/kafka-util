package com.pie.kafka.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.*;
import org.springframework.stereotype.Service;

/**
 * ClassName DynamicKafkaListener
 * Description
 * Date 2021/8/24
 * Author wangxiyue.xy@163.com
 */
@Service
public class DynamicKafkaListener {

    Logger log = LoggerFactory.getLogger(DynamicKafkaListener.class);

    @Autowired
    ConcurrentKafkaListenerContainerFactory factory;


    public void changeTopic( String topic){
        MessageListenerContainer container =  factory.createContainer(topic);
        container.setAutoStartup(true);
        log.info("change topic ===> {}" , topic );
        container.setupMessageListener(new ConsumerAwareMessageListener<String,String>() {

            @Override
            public void onMessage(ConsumerRecord<String, String> data, Consumer<?, ?> consumer) {
                System.out.println(data.key() + "===>" + data.value());
                System.out.println(data.topic());
            }
        });
        container.start();
    }


}
