package ru.neka.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neka.orderservice.dto.OrderRequest;
import ru.neka.orderservice.kafka.OrderNotificationProducer;
import ru.neka.orderservice.model.Order;
import ru.neka.orderservice.repository.OrderRepository;
import ru.neka.orderservice.service.UserServiceClient;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final UserServiceClient userServiceClient;
    private final OrderRepository orderRepository;
    private final OrderNotificationProducer orderNotificationProducer;

    @Autowired
    public OrderController(
            UserServiceClient userServiceClient,
            OrderRepository orderRepository,
            OrderNotificationProducer orderNotificationProducer
    ) {
        this.userServiceClient = userServiceClient;
        this.orderRepository = orderRepository;
        this.orderNotificationProducer = orderNotificationProducer;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        boolean userExists = userServiceClient.checkUserExists(orderRequest.getUserId());
        if (!userExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setOrderNumber(orderRequest.getOrderNumber());
        orderRepository.save(order);

        String orderDetails = String.format("Order created: UserId=%s, OrderNumber=%s", order.getUserId(), order.getOrderNumber());
        orderNotificationProducer.sendOrderNotification(orderDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
    }
}

