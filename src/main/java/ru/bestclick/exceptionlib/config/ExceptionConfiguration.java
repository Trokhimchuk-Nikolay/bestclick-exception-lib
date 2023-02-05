package ru.bestclick.exceptionlib.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.bestclick.exceptionlib.exception.InternalServerException;
import ru.bestclick.exceptionlib.exception.handler.BusinessExceptionHandler;

import static ru.bestclick.exceptionlib.constant.Constants.SERVLET_HANDLING_ENABLED_MSG;
import static ru.bestclick.exceptionlib.constant.ExceptionConstants.REACTOR_NOT_FOUND_CODE;

@Slf4j
@Configuration
@RequiredArgsConstructor
@Import({
        ExceptionConfiguration.ServletConfiguration.class,
        ExceptionConfiguration.ReactiveConfiguration.class,
        MessageSourceConfig.class
})
public class ExceptionConfiguration {
    @RequiredArgsConstructor
    @Import(BusinessExceptionHandler.class)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public static class ServletConfiguration {
        @PostConstruct
        public void init() {
            log.info(SERVLET_HANDLING_ENABLED_MSG);
        }
    }

    //TODO Написать конфигурацию под реактивный стек
    @RequiredArgsConstructor
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    public static class ReactiveConfiguration {
        @PostConstruct
        public void init() {
            throw new InternalServerException(REACTOR_NOT_FOUND_CODE);
        }
    }
}
