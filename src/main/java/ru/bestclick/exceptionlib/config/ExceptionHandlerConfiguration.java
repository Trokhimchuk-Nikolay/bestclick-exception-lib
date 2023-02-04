package ru.bestclick.exceptionlib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.bestclick.exceptionlib.exception.handler.BusinessExceptionHandler;

@Configuration
public class ExceptionHandlerConfiguration {

    @Bean
    public BusinessExceptionHandler businessExceptionHandler() {
        return new BusinessExceptionHandler();
    }
}
