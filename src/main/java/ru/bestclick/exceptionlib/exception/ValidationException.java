package ru.bestclick.exceptionlib.exception;

import java.io.Serial;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.bestclick.exceptionlib.validator.ValidationResult;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationException extends BusinessException {

  @Serial
  private static final long serialVersionUID = -8989998947855086802L;

  private final Set<ValidationResult> validationResults;

  public ValidationException(Set<ValidationResult> validationResults) {
    this.validationResults = validationResults;
  }
}
