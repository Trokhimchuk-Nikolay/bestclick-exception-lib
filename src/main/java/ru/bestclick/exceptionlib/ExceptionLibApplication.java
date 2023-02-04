package ru.bestclick.exceptionlib;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import ru.bestclick.exceptionlib.config.ExceptionHandlerConfiguration;
import ru.bestclick.exceptionlib.helper.MessageHelper;

import java.util.Locale;

@SpringBootApplication()
@ComponentScan(excludeFilters = {@ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE, classes = ExceptionHandlerConfiguration.class)})
@RequiredArgsConstructor
public class ExceptionLibApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExceptionLibApplication.class, args);

        var message = MessageHelper.getMessageWithLocale("config.error.need.reactor.config", Locale.ENGLISH);
        System.out.println(message);
    }

}
