package ru.neka.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neka.userservice.UserServiceGrpc;
import ru.neka.userservice.UserServiceStub;


@Service
public class UserServiceClient {

    private final UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    @Autowired
    public UserServiceClient(UserServiceGrpc.UserServiceBlockingStub userServiceStub) {
        this.userServiceStub = userServiceStub;
    }

    public boolean checkUserExists(String userId) {
        try {
            UserServiceStub.UserResponse response = userServiceStub.getUser(UserServiceStub.UserRequest.newBuilder().setUserId(userId).build());
            return response != null && response.getUserId().equals(userId);
        } catch (Exception e) {
            return false;
        }
    }
}
