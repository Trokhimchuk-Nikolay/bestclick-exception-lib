package ru.bestclick.exceptionlib.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.MessageSourceAccessor;
import ru.bestclick.exceptionlib.util.MessageUtils;

@Configuration
@RequiredArgsConstructor
@Import({MessageUtils.class})
public class MessageSourceConfig {

  @Bean
  @ConditionalOnMissingBean(MessageSourceAccessor.class)
  MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
    return new MessageSourceAccessor(messageSource);
  }
}