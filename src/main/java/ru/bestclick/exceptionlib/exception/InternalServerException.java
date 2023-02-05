package ru.bestclick.exceptionlib.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bestclick.exceptionlib.helper.MessageHelper;

import java.io.Serial;
import java.time.OffsetDateTime;

import static ru.bestclick.exceptionlib.constant.ExceptionConstants.UNKNOWN_SERVICE_ERROR_CODE;

@Data
@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    @Serial
    static final long serialVersionUID = -6572598947855086802L;

    private String code;
    private OffsetDateTime createDate;
    private String[] details;

    public InternalServerException(String code, OffsetDateTime createDate, String[] details) {
        super(MessageHelper.getMessage(code));
        this.code = code;
        this.createDate = createDate;
        this.details = details;
    }

    public InternalServerException(String code, String[] details) {
        super(MessageHelper.getMessage(code));
        this.code = code;
        this.createDate = OffsetDateTime.now();
        this.details = details;
    }

    public InternalServerException(String code) {
        super(MessageHelper.getMessage(code));
        this.code = code;
        this.createDate = OffsetDateTime.now();
    }

    public InternalServerException() {
        super(MessageHelper.getMessage(UNKNOWN_SERVICE_ERROR_CODE));
        this.code = UNKNOWN_SERVICE_ERROR_CODE;
        this.createDate = OffsetDateTime.now();
    }
}
