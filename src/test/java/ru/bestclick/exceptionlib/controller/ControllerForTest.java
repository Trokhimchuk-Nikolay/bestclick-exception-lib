package ru.bestclick.exceptionlib.controller;

import static ru.bestclick.exceptionlib.constants.Constant.TEST_PATH;
import static ru.bestclick.exceptionlib.constants.ExceptionConstants.TEST_SERVICE_ERROR_CODE;
import static ru.bestclick.exceptionlib.constants.JsonConstants.API_ERROR_RQ_SUCCESS_RU;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bestclick.exceptionlib.model.ApiError;
import ru.bestclick.exceptionlib.exception.BusinessException;
import ru.bestclick.exceptionlib.exception.ServerException;

@Slf4j
@RestController
@RequestMapping(TEST_PATH)
@RequiredArgsConstructor
public class ControllerForTest {

  private final ObjectMapper mapper;

  @SneakyThrows
  @GetMapping(headers = "Accept=application/json")
  public ApiError successfulCall() {
    return mapper.readValue(new File(API_ERROR_RQ_SUCCESS_RU), ApiError.class);
  }

  @DeleteMapping
  public void errorBusinessCall() {
    throw new BusinessException(TEST_SERVICE_ERROR_CODE);
  }

  @PutMapping
  public void errorServerCall() {
    throw new ServerException(TEST_SERVICE_ERROR_CODE);
  }
}