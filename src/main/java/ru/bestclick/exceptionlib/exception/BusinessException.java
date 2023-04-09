package ru.bestclick.exceptionlib.exception;

import static ru.bestclick.exceptionlib.constant.ExceptionConstants.UNKNOWN_SERVICE_ERROR_CODE;

import java.io.Serial;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.bestclick.exceptionlib.util.MessageUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

  @Serial
  static final long serialVersionUID = -3332598979055089070L;

  private final String code;
  private final OffsetDateTime createDate;
  private String[] details;

  public BusinessException(String code, OffsetDateTime createDate, String[] details) {
    super(MessageUtils.getMessage(code, details));
    this.code = code;
    this.createDate = createDate;
  }

  public BusinessException(String code, String[] details) {
    super(MessageUtils.getMessage(code, details));
    this.code = code;
    this.createDate = OffsetDateTime.now();
  }

  public BusinessException(String code) {
    super(MessageUtils.getMessage(code));
    this.code = code;
    this.createDate = OffsetDateTime.now();
  }

  public BusinessException() {
    super(MessageUtils.getMessage(UNKNOWN_SERVICE_ERROR_CODE));
    this.code = UNKNOWN_SERVICE_ERROR_CODE;
    this.createDate = OffsetDateTime.now();
  }
}
