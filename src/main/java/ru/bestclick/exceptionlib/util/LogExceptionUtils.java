package ru.bestclick.exceptionlib.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;

@Slf4j
@UtilityClass
public class LogExceptionUtils {

  public static void logException(Throwable ex) {
    log.error("In logException: ", ex);
  }

  public static void logException(Marker marker, Throwable ex) {
    log.error(marker, "In logException: ", ex);
  }
}
