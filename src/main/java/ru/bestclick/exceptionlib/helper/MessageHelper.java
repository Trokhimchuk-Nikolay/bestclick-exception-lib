package ru.bestclick.exceptionlib.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static ru.bestclick.exceptionlib.constant.ExceptionConstants.GETTING_MESSAGE_FROM_RESOURCES_ERROR_MSG;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHelper {
    private static MessageSource source;

    @Autowired
    public void setMessageSourceAccessor(MessageSource messageSource) {
        source = messageSource;
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        try {
            return source.getMessage(code, args, locale);
        } catch (Exception ex) {
            log.error("Error getting message from resources: {}", ex.getMessage());
            return GETTING_MESSAGE_FROM_RESOURCES_ERROR_MSG;
        }
    }

    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, Locale.ROOT);
    }

    public static String getMessage(String code) {
        return getMessage(code, null, Locale.ROOT);
    }

    public static String getMessageWithLocale(String code, Locale locale) {
        return getMessage(code, null, locale);
    }
}
