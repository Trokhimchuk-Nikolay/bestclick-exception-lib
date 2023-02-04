package ru.bestclick.exceptionlib.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {"code", "message", "details", "createDate"})
public class ApiError {
    private String code;
    private String message;
    private String[] details;

    @Builder.Default
    private OffsetDateTime createDate = OffsetDateTime.now();
}
