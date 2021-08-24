package com.pie.kafka.controller;

import com.pie.kafka.listener.DynamicKafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName ChangeKafkaTopic
 * Description
 * Date 2021/8/24
 * Author wangxiyue.xy@163.com
 */
@RestController
@RequestMapping("/kafka")
public class ChangeKafkaTopic {

    @Autowired
    DynamicKafkaListener listener;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/c")
    public String change(String topic){
        listener.changeTopic(topic);
        return "ok";
    }



    @RequestMapping("/s")
    public String send(String topic,String msg){
        kafkaTemplate.send(topic , msg);
        return msg;
    }
}
