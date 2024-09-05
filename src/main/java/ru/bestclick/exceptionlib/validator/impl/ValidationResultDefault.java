package ru.bestclick.exceptionlib.validator.impl;

import ru.bestclick.exceptionlib.validator.ValidationResult;


public record ValidationResultDefault(String code, Object[] args) implements ValidationResult {

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public Object[] getArgs() {
    return this.args;
  }
}
