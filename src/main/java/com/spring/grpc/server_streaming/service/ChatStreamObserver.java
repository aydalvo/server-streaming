package com.spring.grpc.server_streaming.service;

import com.proto.generated.banking.ChatMessage;
import com.proto.generated.banking.ChatResponse;
import io.grpc.stub.StreamObserver;

public class ChatStreamObserver implements StreamObserver<ChatMessage> {

    private final StreamObserver<ChatResponse> responseObserver;

    public ChatStreamObserver(StreamObserver<ChatResponse> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(ChatMessage chatMessage) {
        // Handle the client's message
        String sender = chatMessage.getSender();
        String message = chatMessage.getMessage();

        System.out.println("Received message from: " + sender);
        System.out.println("Message: " + message);

        ChatResponse.Builder response = ChatResponse.newBuilder();

        if (message.equals("Hi")) {
            response.setSender("Server");
            response.setMessage("Hello how can i help you today.");
        } else if (message.equals("I need to know my account balance")) {
            response.setSender("Server");
            response.setMessage("Ok. Please type the otp recieved on your registered phone");
        } else if (message.equals("5050")) {
            response.setSender("Server");
            response.setMessage("Ok. The account balance is 300");
        } else {
            response.setSender("Server");
            response.setMessage("Invalid OTP");
        }

        responseObserver.onNext(response.build());
    }

    @Override
    public void onError(Throwable throwable) {
        // Handle any errors
    }

    @Override
    public void onCompleted() {
        // Complete the response stream
        responseObserver.onCompleted();
    }
}
