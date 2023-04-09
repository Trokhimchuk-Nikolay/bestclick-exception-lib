package ru.bestclick.exceptionlib.properties;

import lombok.Value;

@Value
public class BestclickExceptionLogError {

  boolean business;
  boolean server;
}
