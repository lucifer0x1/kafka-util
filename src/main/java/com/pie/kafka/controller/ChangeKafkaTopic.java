package com.pie.kafka.controller;

import com.pie.kafka.listener.DynamicKafkaListener;
import com.pie.kafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

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
    KafkaService service;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/c")
    public String change(String topic){
        service.changeTopic(topic);
        return "ok";
    }

    @RequestMapping("/d")
    public String distroy(String topic){
        return service.distroy(topic);
    }


    @RequestMapping("/s")
    public String send(String topic,String msg){
        kafkaTemplate.send(topic , msg);
        return msg;
    }
}
