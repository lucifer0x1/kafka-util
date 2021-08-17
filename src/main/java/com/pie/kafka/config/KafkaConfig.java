package com.pie.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @wangxiyue.xy@163.com
 * @Date 2021/8/17
 * @Author 虫子
 * @ClassMame: KafkaConfig
 * @Description: kafka 配置初始化
 */
@Configuration
public class KafkaConfig {

    public final static  String topicName =  "test.topic";

    @Bean
    public NewTopic testTopic(){
        //Topic命名，分区数，副本数
        return new NewTopic(topicName,8,(short)1);
    }


}
