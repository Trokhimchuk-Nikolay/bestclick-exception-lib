package ru.bestclick.exceptionlib.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {"message", "details", "createDate"})
public class ApiError implements Serializable {

  @Serial
  private static final long serialVersionUID = -1142598979055089070L;

  String message;
  String[] details;

  @Builder.Default
  OffsetDateTime createDate = OffsetDateTime.now();
}
