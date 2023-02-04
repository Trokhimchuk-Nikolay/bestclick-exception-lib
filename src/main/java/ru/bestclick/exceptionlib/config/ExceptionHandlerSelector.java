package ru.bestclick.exceptionlib.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import static java.util.Objects.requireNonNull;
import static ru.bestclick.exceptionlib.constant.Constants.REACTIVE_STACK;
import static ru.bestclick.exceptionlib.constant.Constants.SERVLET_STACK;
import static ru.bestclick.exceptionlib.constant.ExceptionConstants.REACTOR_NOT_FOUND_CODE;
import static ru.bestclick.exceptionlib.constant.ExceptionConstants.UNSUPPORTED_CONFIG_CODE;

/*TODO Написать конфигурацию для reactive-web*/
public class ExceptionHandlerSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableExceptionHandler.class.getName())
        );
        String criteria = requireNonNull(attributes).getString("criteria");
        return switch (criteria) {
            case SERVLET_STACK -> new String[]{"ru.bestclick.exceptionlib.config.ExceptionHandlerConfiguration"};
            case REACTIVE_STACK -> throw new UnsupportedOperationException(REACTOR_NOT_FOUND_CODE);
            default -> throw new UnsupportedOperationException(UNSUPPORTED_CONFIG_CODE);
        };
    }
}
