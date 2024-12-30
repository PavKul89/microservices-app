package ru.neka.notificationservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @KafkaListener(topics = "order-notifications", groupId = "notification-group")
    public void listenKafkaTopic(String message) {
        System.out.println("Получено сообщение: " + message);
    }

}
