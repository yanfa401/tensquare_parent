package com.example.rabbitmq_demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDemoApplicationTests {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public static final String ROUTINGKEY = "itcast";
    
    public static final String EXCHANGE = "topictest";
    
    @Test
    public void contextLoads() {
        rabbitTemplate.convertAndSend("topictest", "goods.log", "主题模式");
        System.out.println("send ok");
    }
    
}
