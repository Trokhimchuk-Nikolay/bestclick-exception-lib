package ru.bestclick.exceptionlib.util;

import java.util.Set;
import lombok.experimental.UtilityClass;
import ru.bestclick.exceptionlib.exception.ValidationException;
import ru.bestclick.exceptionlib.validator.ValidationResult;

@UtilityClass
public class ValidationUtils {

  public static void checkResult(Set<ValidationResult> result) {
    if (result.isEmpty()) {
      return;
    }
    throw new ValidationException(result);
  }
}
