package ru.bestclick.exceptionlib.constant;

import lombok.experimental.UtilityClass;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

//TODO Перенести в общую либу
@UtilityClass
public class MarkerConstants {

  public static final Marker CONTROLLER_MARKER = MarkerFactory.getMarker("CONTROLLER_MARKER");
  public static final Marker CONVERTER_MARKER = MarkerFactory.getMarker("CONVERTER_MARKER");
  public static final Marker SERVICE_MARKER = MarkerFactory.getMarker("SERVICE_MARKER");
  public static final Marker REPOSITORY_MARKER = MarkerFactory.getMarker("REPOSITORY_MARKER");
  public static final Marker HANDLER_MARKER = MarkerFactory.getMarker("HANDLER_MARKER");
  public static final Marker SECURITY_MARKER = MarkerFactory.getMarker("SECURITY_MARKER");
}
