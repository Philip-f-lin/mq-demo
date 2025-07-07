package com.itheima.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue(){
        // 1. 隊列名
        String queueName = "simple.queue";

        // 2. 消息
        String message = "hello, spring amqp!";

        // 3. 發送消息
        rabbitTemplate.convertAndSend(queueName, message);

    }

    @Test
    public void testWorkQueue(){
        // 1. 隊列名
        String queueName = "work.queue";

        for (int i = 1; i <= 50 ; i++) {
            // 2. 消息
            String message = "hello, spring amqp_" + i ;

            // 3. 發送消息
            rabbitTemplate.convertAndSend(queueName, message);
        }
    }

    @Test
    public void testFanoutQueue(){
        // 1. 交換機名
        String exchangeName = "hmall.fanout";

        // 2. 消息
        String message = "hello, everyone!";

        // 3. 發送消息
        rabbitTemplate.convertAndSend(exchangeName, null, message);

    }

    @Test
    public void testDirectQueue(){
        // 1. 交換機名
        String exchangeName = "hamll.direct";

        // 2. 消息
        String message = "藍色!";

        // 3. 發送消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);

    }
}