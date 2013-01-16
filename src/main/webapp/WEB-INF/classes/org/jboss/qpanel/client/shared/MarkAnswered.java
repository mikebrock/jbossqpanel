package org.jboss.qpanel.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.marshalling.client.api.annotations.MapsTo;

/**
 * @author Mike Brock
 */
@Portable
public class MarkAnswered {
  private int id;

  public MarkAnswered(@MapsTo("id") int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
