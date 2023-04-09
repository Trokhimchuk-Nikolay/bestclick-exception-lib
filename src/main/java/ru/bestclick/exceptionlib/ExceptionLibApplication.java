package ru.bestclick.exceptionlib;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class ExceptionLibApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExceptionLibApplication.class, args);
  }

}
