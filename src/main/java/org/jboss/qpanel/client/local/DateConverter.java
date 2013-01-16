package org.jboss.qpanel.client.local;

import com.google.gwt.i18n.client.DateTimeFormat;
import org.jboss.errai.databinding.client.api.Converter;

import java.util.Date;

/**
 * @author Mike Brock
 */
public class DateConverter implements Converter<Date, String> {
  @Override
  public Date toModelValue(String widgetValue) {
    throw new UnsupportedOperationException("not supported");
  }

  @Override
  public String toWidgetValue(Date modelValue) {
    return DateTimeFormat.getFormat("hh:mm:ss").format(modelValue);
  }
}
