package ru.bestclick.exceptionlib.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bestclick.exceptionlib.exception.ApiError;
import ru.bestclick.exceptionlib.exception.BusinessException;

import java.io.File;

import static ru.bestclick.exceptionlib.constants.Constant.TEST_PATH;
import static ru.bestclick.exceptionlib.constants.ExceptionConstants.TEST_SERVICE_ERROR_CODE;
import static ru.bestclick.exceptionlib.constants.JsonConstants.API_ERROR_RQ_SUCCESS_RU;

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
    public void errorCall() {
        throw new BusinessException(TEST_SERVICE_ERROR_CODE);
    }
}