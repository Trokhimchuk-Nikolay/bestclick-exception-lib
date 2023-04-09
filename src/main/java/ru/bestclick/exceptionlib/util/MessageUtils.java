package ru.bestclick.exceptionlib.util;

import static ru.bestclick.exceptionlib.constant.ExceptionConstants.GETTING_MESSAGE_FROM_RESOURCES_ERROR_MSG;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;

@Slf4j
@RequiredArgsConstructor
public class MessageUtils {

  private static MessageSourceAccessor messageSourceAccessor;

  @Autowired
  private void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
    MessageUtils.messageSourceAccessor = messageSourceAccessor;
  }

  public static String getMessage(String code, Object[] args, Locale locale) {
    try {
      return messageSourceAccessor.getMessage(code, args, locale);
    } catch (Exception ex) {
      log.error("Error getting message from resources. code: {}, locale: {}, exception:",
          code, locale, ex);
      return GETTING_MESSAGE_FROM_RESOURCES_ERROR_MSG;
    }
  }

  public static String getMessage(String code, Object[] args) {
    return getMessage(code, args, Locale.ROOT);
  }

  public static String getMessage(String code) {
    return getMessage(code, null, ThreadLocalStorage.LOCALE.get());
  }

  public static String getMessageWithLocale(String code, Locale locale) {
    return getMessage(code, null, locale);
  }
}
