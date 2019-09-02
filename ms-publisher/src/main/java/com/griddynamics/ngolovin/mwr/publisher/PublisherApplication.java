package com.griddynamics.ngolovin.mwr.publisher;

import com.griddynamics.ngolovin.mwr.commons.models.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class PublisherApplication {

    private static final int MESSAGE_BATCH_SIZE = 10;
    private static final AtomicLong SENT_MESSAGE_COUNTER = new AtomicLong(0);

    private final RabbitTemplate rabbitTemplate;

    @Value("${mwr.publisher.rabbitmq.exchange}")
    private String exchange;
    @Value("${mwr.publisher.rabbitmq.routing-key}")
    private String routingKey;

    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class, args);
    }

    @Scheduled(fixedDelay = 1000)
    private void sendMessages() {
        for (int i = 0; i < MESSAGE_BATCH_SIZE; i++) {
            String payload = "Payload #" + System.currentTimeMillis();
            Message message = new Message(SENT_MESSAGE_COUNTER.incrementAndGet(), payload);
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            log.info("Sent routingKey={} message={}", routingKey, message);
        }
    }
}
