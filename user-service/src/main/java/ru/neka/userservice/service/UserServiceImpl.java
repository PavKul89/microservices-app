package ru.neka.userservice.service;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;
import ru.neka.userservice.UserServiceGrpc;
import ru.neka.userservice.UserServiceStub;
import ru.neka.userservice.model.User;
import ru.neka.userservice.repository.UserRepository;

import java.util.Optional;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUser(UserServiceStub.UserRequest request, StreamObserver<UserServiceStub.UserResponse> responseObserver) {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(request.getUserId()));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserServiceStub.UserResponse response = UserServiceStub.UserResponse.newBuilder()
                    .setUserId(String.valueOf(user.getId()))
                    .setName(user.getName())
                    .setEmail(user.getPhoneNumber())
                    .build();

            responseObserver.onNext(response);
        } else {
            responseObserver.onError(
                    new RuntimeException("User not found with ID: " + request.getUserId())
            );
        }

        responseObserver.onCompleted();
    }
}
