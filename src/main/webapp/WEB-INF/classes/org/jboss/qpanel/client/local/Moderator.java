package org.jboss.qpanel.client.local;

import com.google.gwt.user.client.ui.RootPanel;
import org.jboss.errai.ioc.client.api.EntryPoint;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Mike Brock
 */
@EntryPoint
public class Moderator {
  @Inject RootPanel panel;
  @Inject QuestionPanel questionPanel;
  @Inject SessionControl sessionControl;

  @PostConstruct
  public void init() {
    panel.add(questionPanel);
  }
}
