package ru.bestclick.exceptionlib.config;

import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.bestclick.exceptionlib.constant.Constants.SERVLET_STACK;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ExceptionHandlerSelector.class)
public @interface EnableExceptionHandler {
    @NonNull
    String criteria() default SERVLET_STACK;
}
