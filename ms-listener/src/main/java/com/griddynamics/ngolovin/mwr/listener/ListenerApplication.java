package com.griddynamics.ngolovin.mwr.listener;

import com.griddynamics.ngolovin.mwr.commons.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
@Slf4j
public class ListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListenerApplication.class, args);
    }

    @RabbitListener(queues = "${mwr.listener.rabbitmq.queue}")
    private void onMessage(Message message) {
        log.info("Received message={}", message);
    }
}
