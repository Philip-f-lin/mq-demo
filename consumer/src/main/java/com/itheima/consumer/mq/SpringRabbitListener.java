package com.itheima.consumer.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String message){
        log.info("消費者 1 監聽到 fanout.queue1 的消息: {}", message);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String message){
        log.info("消費者 2 監聽到 fanout.queue2 的消息: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1", durable = "true"),
            exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String message){
        log.info("消費者 1 監聽到 direct.queue1 的消息: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2", durable = "true"),
            exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    @RabbitListener(queues = "direct.queue2")
    public void listenDirectQueue2(String message){
        log.info("消費者 2 監聽到 direct.queue2 的消息: {}", message);
    }

}
