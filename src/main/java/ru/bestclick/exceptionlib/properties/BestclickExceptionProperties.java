package ru.bestclick.exceptionlib.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "bestclick.exception")
public class BestclickExceptionProperties {

  boolean enable;

}
