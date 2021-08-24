package com.pie.kafka.service;

import com.pie.kafka.listener.DynamicKafkaListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConsumerAwareMessageListener;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName KafkaService
 * Description
 * Date 2021/8/24
 * Author wangxiyue.xy@163.com
 */
@Service
public class KafkaService {

    Logger log = LoggerFactory.getLogger(DynamicKafkaListener.class);

    @Autowired
    ConcurrentKafkaListenerContainerFactory factory;

    @Autowired
    DynamicKafkaListener listener;

    ConcurrentHashMap<String,MessageListenerContainer> listenerMaps = new ConcurrentHashMap();


    public void changeTopic( String topic){

        if(!listenerMaps.containsKey(topic)){
            log.info("There is no the same topic ===> {}" ,topic);
            MessageListenerContainer container =  factory.createContainer(topic);
            container.setAutoStartup(true);
            //使用service 固定listener方法
            container.setupMessageListener(listener);
//            container.setupMessageListener( new DynamicKafkaListener());
            container.start();
            listenerMaps.put(topic,container);
        }else {
            log.info("The topic ===> {}  is exits",topic);
        }
    }

    public String distroy(String topic) {
        if(listenerMaps.containsKey(topic)){
            log.info("Find topic by [{}]",topic);
            MessageListenerContainer container  = listenerMaps.get(topic);
            container.stop();
            log.info("Topic [{}] status is {}",topic,container.isRunning());
            listenerMaps.remove(topic);
        }else {
            log.info("Can not find topic [{}]",topic);
        }
        return "ok";

    }
}
