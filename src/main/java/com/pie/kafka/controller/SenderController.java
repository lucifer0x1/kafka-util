package com.pie.kafka.controller;

import com.pie.kafka.config.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/send")
    public String index(String msg){
        kafkaTemplate.send(KafkaConfig.topicName,msg);
        return msg;
    }
}
