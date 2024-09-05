package ru.bestclick.exceptionlib.helper;

import static ru.bestclick.exceptionlib.constant.ExceptionCodeConstants.GETTING_MESSAGE_FROM_RESOURCES_ERROR_MSG;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;

@Slf4j
@RequiredArgsConstructor
public class MessageAccessorHelper {

  private final MessageSourceAccessor messageSourceAccessor;

  private static Locale getLocale(Locale locale) {
    if (locale != null && locale.getLanguage() != null && !locale.getLanguage().isBlank()) {
      return locale;
    }
    if (ThreadLocalStorage.getLocale() != null) {
      return ThreadLocalStorage.getLocale();
    }
    return Locale.getDefault();
  }

  public String getMessage(String code, Object[] args, Locale locale) {
    try {
      locale = getLocale(locale);
      return messageSourceAccessor.getMessage(code, args, locale);
    } catch (Exception ex) {
      log.error("Error getting message from resources. code: {}, locale: {}, exception:",
          code, locale, ex);
      return GETTING_MESSAGE_FROM_RESOURCES_ERROR_MSG;
    }
  }

  public String getMessage(String code, Object[] args) {
    return getMessage(code, args, Locale.ROOT);
  }

  public String getMessage(String code) {
    return getMessage(code, null, ThreadLocalStorage.getLocale());
  }

  public String getMessage(String code, Locale locale) {
    return getMessage(code, null, locale);
  }
}
