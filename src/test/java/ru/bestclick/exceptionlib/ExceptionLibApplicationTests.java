package ru.bestclick.exceptionlib;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.bestclick.exceptionlib.config.EnableExceptionHandler;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;
import ru.bestclick.exceptionlib.exception.ApiError;

import java.io.File;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.bestclick.exceptionlib.constants.Constant.TEST_PATH;
import static ru.bestclick.exceptionlib.constants.JsonConstants.*;

@AutoConfigureMockMvc
@EnableExceptionHandler
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExceptionLibApplicationTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("Successful test controller call")
    @SneakyThrows
    void successCall() {
        var apiError = mapper.readValue(new File(API_ERROR_RQ_SUCCESS_RU), ApiError.class);

        mvc.perform(get(TEST_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(apiError.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(apiError.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createDate").isNotEmpty());
    }

    @Test
    @DisplayName("Calling a test controller with an error(LOCALE ROOT)")
    @SneakyThrows
    void errorCallLocaleRoot() {
        var apiError = mapper.readValue(new File(API_ERROR_RQ_ERROR_RU), ApiError.class);

        mvc.perform(delete(TEST_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(apiError.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(apiError.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createDate").isNotEmpty());
    }

    @Test
    @DisplayName("Calling a test controller with an error(LOCALE EN)")
    @SneakyThrows
    void errorCallLocaleEn() {
        var apiError = mapper.readValue(new File(API_ERROR_RQ_ERROR_EN), ApiError.class);
        ThreadLocalStorage.locale.set(Locale.ENGLISH);

        mvc.perform(delete(TEST_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(apiError.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(apiError.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createDate").isNotEmpty());
    }
}
