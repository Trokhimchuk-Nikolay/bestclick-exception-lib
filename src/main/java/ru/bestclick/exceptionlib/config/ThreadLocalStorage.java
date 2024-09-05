package ru.bestclick.exceptionlib.config;

import java.time.ZoneId;
import java.util.Locale;
import java.util.UUID;
import lombok.Value;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

@Value
@UtilityClass
public class ThreadLocalStorage {

  private static final ThreadLocal<Locale> LOCALE = ThreadLocal.withInitial(() -> Locale.ROOT);
  private static final ThreadLocal<UUID> TENANT = ThreadLocal.withInitial(() -> null);
  private static final ThreadLocal<UUID> USER = new ThreadLocal<>();
  private static final ThreadLocal<String> APPLICATION = new ThreadLocal<>();
  private static final ThreadLocal<Boolean> ADMIN = ThreadLocal.withInitial(() -> Boolean.FALSE);
  private static final ThreadLocal<ZoneId> TIME_ZONE = ThreadLocal.withInitial(
      () -> ZoneId.of("Europe/Moscow"));

  public Locale getLocale() {
    return LOCALE.get();
  }

  public void setLocale(@Nullable Locale locale) {
    if (locale != null) {
      LOCALE.set(locale);
    } else {
      removeLocale();
    }
  }

  public UUID getTenantId() {
    return TENANT.get();
  }

  public void setTenantId(@Nullable UUID tenantId) {
    if (tenantId != null) {
      TENANT.set(tenantId);
    } else {
      removeTenantId();
    }

  }

  public UUID getUserId() {
    return USER.get();
  }

  public void setUserId(@Nullable UUID userId) {
    if (userId != null) {
      USER.set(userId);
    } else {
      removeUserId();
    }

  }

  public String getApplication() {
    return APPLICATION.get();
  }

  public void setApplication(@Nullable String application) {
    if (application != null && !application.isBlank()) {
      APPLICATION.set(application);
    } else {
      removeApplication();
    }

  }

  public boolean isAdmin() {
    return Boolean.TRUE.equals(ADMIN.get());
  }

  public void setAdmin(@Nullable Boolean isAdmin) {
    if (Boolean.TRUE.equals(isAdmin)) {
      ADMIN.set(isAdmin);
    } else {
      removeAdmin();
    }
  }

  public ZoneId getZoneId() {
    return TIME_ZONE.get();
  }

  public void setTimeZone(@Nullable ZoneId zone) {
    if (zone != null) {
      TIME_ZONE.set(zone);
    } else {
      removeZoneId();
    }
  }

  private static void removeZoneId() {
    TIME_ZONE.remove();
  }

  private static void removeAdmin() {
    ADMIN.remove();
  }

  private static void removeApplication() {
    APPLICATION.remove();
  }

  public static void removeUserId() {
    USER.remove();
  }

  public void removeTenantId() {
    TENANT.remove();
  }

  public static void removeLocale() {
    LOCALE.remove();
  }

  public void removeAll() {
    removeTenantId();
    removeUserId();
    removeAdmin();
    removeLocale();
    removeZoneId();
    removeApplication();
  }
}