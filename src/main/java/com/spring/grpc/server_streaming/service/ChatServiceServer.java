package com.spring.grpc.server_streaming.service;

import com.proto.generated.banking.ChatMessage;
import com.proto.generated.banking.ChatResponse;
import com.proto.generated.banking.ChatServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ChatServiceServer extends ChatServiceGrpc.ChatServiceImplBase {

    @Override
    public StreamObserver<ChatMessage> startChat(StreamObserver<ChatResponse> responseObserver) {
        return new ChatStreamObserver(responseObserver);
    }
}
