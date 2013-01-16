package org.jboss.qpanel.client.local;

import org.jboss.qpanel.client.shared.UserData;

/**
 * @author Mike Brock
 */
public class LoggedIn {
  private UserData userData;

  public LoggedIn(UserData userData) {
    this.userData = userData;
  }

  public UserData getUserData() {
    return userData;
  }
}
