package ru.gb_spring.books.services;

import ru.gb_spring.books.models.Book;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Интерфейс интеграции
 */
@MessagingGateway(defaultRequestChannel = "textInputChanel")
public interface FileGateway {
    /**
     * Запись данных в канал интеграции
     * @param filename
     * @param book
     */
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, Book book);
}
