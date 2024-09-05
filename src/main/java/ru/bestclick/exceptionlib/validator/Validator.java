package ru.bestclick.exceptionlib.validator;

import org.springframework.lang.Nullable;

public interface Validator<R extends ValidationResult> {

  @Nullable
  R validate(Object value);

  String getType();
}
