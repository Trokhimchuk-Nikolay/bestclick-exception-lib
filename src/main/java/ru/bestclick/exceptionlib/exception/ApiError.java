package ru.bestclick.exceptionlib.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {"code", "message", "details", "createDate"})
public class ApiError implements Serializable {
    @Serial
    static final long serialVersionUID = -1142598979055089070L;

    @Builder.Default
    private OffsetDateTime createDate = OffsetDateTime.now();

    private String code;
    private String message;
    private String[] details;


}
