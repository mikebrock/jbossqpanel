package org.jboss.qpanel.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.marshalling.client.api.annotations.MapsTo;

/**
 * @author Mike Brock
 */
@Portable
public class UserJoin {
  private final UserData userData;

  public UserJoin(@MapsTo("userData") UserData userData) {
    this.userData = userData;
  }

  public UserData getUserData() {
    return userData;
  }
}
