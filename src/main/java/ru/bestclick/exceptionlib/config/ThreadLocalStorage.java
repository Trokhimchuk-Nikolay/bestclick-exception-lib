package ru.bestclick.exceptionlib.config;

import lombok.Data;

import java.util.Locale;

@Data
public class ThreadLocalStorage {

    public static final ThreadLocal<Locale> locale = ThreadLocal.withInitial(() -> Locale.ROOT);
}