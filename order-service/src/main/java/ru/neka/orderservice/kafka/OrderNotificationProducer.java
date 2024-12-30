package ru.neka.orderservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderNotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;


    private static final String TOPIC = "order-notifications";

    public OrderNotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendOrderNotification(String orderDetails) {
        kafkaTemplate.send(TOPIC, orderDetails);
    }
}
