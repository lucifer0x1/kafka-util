package com.pie.kafka.controller;

import com.pie.kafka.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @wangxiyue.xy@163.com
 * @Date 2021/7/24
 * @Author 虫子
 * @ClassMame: SenderController
 * @Description:
 */
@RestController
@RequestMapping("/")
public class SenderController {

    Logger log = LoggerFactory.getLogger(SenderController.class);

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/send")
    public void index(String msg){
        kafkaTemplate.send(KafkaConfig.topicName,msg);
//        log.debug("start while true to send kafka message");
//        long n = 0;
//        while(true){
//            n++;
//            kafkaTemplate.send(KafkaConfig.topicName,new Date().toString());
//            log.info("send msg  count = {}",n);
//        }
//        return msg;
    }
}
