package ru.bestclick.exceptionlib.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;

import java.util.Locale;

import static ru.bestclick.exceptionlib.constant.ExceptionConstants.GETTING_MESSAGE_FROM_RESOURCES_ERROR_MSG;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHelper {
    private static MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        MessageHelper.messageSourceAccessor = messageSourceAccessor;
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        try {
            return messageSourceAccessor.getMessage(code, args, locale);
        } catch (Exception ex) {
            log.error("Error getting message from resources: {}. code: {}, locale: {}", ex.getMessage(), code, locale);
            return GETTING_MESSAGE_FROM_RESOURCES_ERROR_MSG;
        }
    }

    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, Locale.ROOT);
    }

    public static String getMessage(String code) {
        return getMessage(code, null, ThreadLocalStorage.locale.get());
    }

    public static String getMessageWithLocale(String code, Locale locale) {
        return getMessage(code, null, locale);
    }
}
