package ru.gb_spring.tasks.services;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;

@MessagingGateway(defaultReplyChannel = "textInputChannel")
public interface FileGateway {

    void writeToFile(@Header(FileHeaders.FILENAME) String filename, String data);

}
