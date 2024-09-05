package ru.bestclick.exceptionlib.config;

import static ru.bestclick.exceptionlib.constant.ExceptionCodeConstants.REACTOR_NOT_FOUND_CODE;
import static ru.bestclick.exceptionlib.constant.ExceptionMessageConstants.SERVLET_HANDLING_ENABLED_MSG;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.bestclick.exceptionlib.exception.ServerException;
import ru.bestclick.exceptionlib.handler.BusinessExceptionHandler;
import ru.bestclick.exceptionlib.properties.BestclickExceptionProperties;

@Slf4j
@Configuration
@Import({
    ExceptionConfiguration.ServletConfiguration.class,
    ExceptionConfiguration.ReactiveConfiguration.class,
    MessageSourceConfig.class,
    BestclickExceptionProperties.class
})
public class ExceptionConfiguration {

  @RequiredArgsConstructor
  @Import(value = {BusinessExceptionHandler.class, BestclickExceptionProperties.class})
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
      throw new ServerException(REACTOR_NOT_FOUND_CODE);
    }
  }
}
