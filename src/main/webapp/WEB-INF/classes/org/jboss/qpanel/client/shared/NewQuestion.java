package org.jboss.qpanel.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.marshalling.client.api.annotations.MapsTo;

/**
 * @author Mike Brock
 */
@Portable
public class NewQuestion {
  private final Question question;

  public NewQuestion(@MapsTo("question") Question question) {
    this.question = question;
  }

  public Question getQuestion() {
    return question;
  }
}
