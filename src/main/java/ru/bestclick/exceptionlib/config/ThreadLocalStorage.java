package ru.bestclick.exceptionlib.config;

import java.util.Locale;
import lombok.Data;
import lombok.experimental.UtilityClass;

@Data
@UtilityClass
public class ThreadLocalStorage {

  public static final ThreadLocal<Locale> LOCALE = ThreadLocal.withInitial(() -> Locale.ROOT);
}