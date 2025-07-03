package com.itheima.consumer.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String message){
        log.info("監聽到 simple.queue 的消息: {}", message);
    }

    @RabbitListener(queues = "work.queue")
    public void listenSimpleQueue1(String message){
        log.info("消費者 1 接收到消息: " + message + ", " + LocalTime.now());
    }

    @RabbitListener(queues = "work.queue")
    public void listenSimpleQueue2(String message){
        log.info("消費者 2 ....... 接收到消息: " + message + ", " + LocalTime.now());
    }
}
