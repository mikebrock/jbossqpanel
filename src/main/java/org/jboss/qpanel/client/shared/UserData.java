package org.jboss.qpanel.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.jboss.errai.marshalling.client.api.annotations.MapsTo;

/**
 * @author Mike Brock
 */
@Portable @Bindable
public class UserData {
  private String name;
  private String email;

  public UserData() {
  }

  public UserData(@MapsTo("name") String name, @MapsTo("email") String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "UserData{" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
