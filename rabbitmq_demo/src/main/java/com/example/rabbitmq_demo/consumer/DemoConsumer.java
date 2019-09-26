package com.example.rabbitmq_demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/09/26
 */
@Component
@RabbitListener(queues = "itcast")
public class DemoConsumer {
    
    @RabbitHandler
    public void consume(String msg) {
        System.out.println("queue ==> itcast:" + msg);
    }
}
