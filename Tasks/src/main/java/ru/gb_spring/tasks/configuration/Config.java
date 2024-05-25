package ru.gb_spring.tasks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class Config {

    @Bean
    public MessageChannel textInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriterChannel() {
        return new DirectChannel();
    }

    @Transformer(inputChannel = "textInputChannel", outputChannel = "fileWriterChannel")
    @Bean
    public GenericTransformer<String, String> mainTransformer() {
        return text -> {
            text = text.toUpperCase();
            return text;
        };
    }

    @ServiceActivator(inputChannel = "fileWriterChannel")
    @Bean
    public FileWritingMessageHandler myHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("../resources/"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }

}
