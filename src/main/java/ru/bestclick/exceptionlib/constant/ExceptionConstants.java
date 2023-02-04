package ru.bestclick.exceptionlib.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionConstants {
    public static final String REACTOR_NOT_FOUND_CODE = "config.error.need.reactor.config";
    public static final String UNSUPPORTED_CONFIG_CODE = "config.error.unsupported.handling_configuration";
    public static final String UNKNOWN_SERVICE_ERROR_CODE = "api.error.unknown.service.error";
    public static final String GETTING_MESSAGE_FROM_RESOURCES_ERROR_MSG = "Error getting message from resources";
}
