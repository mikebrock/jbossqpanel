package org.jboss.qpanel.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.marshalling.client.api.annotations.MapsTo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mike Brock
 */
@Portable
public class AuthenticationResponse {
  private final boolean authenticated;
  private final String message;
  private final UserData userData;
  private final List<UserData> userSessions;
  private final List<Question> questions;
  private final boolean moderator;

  public AuthenticationResponse(@MapsTo("authenticated") boolean authenticated,
                                @MapsTo("message") String message,
                                @MapsTo("userData") UserData userData,
                                @MapsTo("userSessions") List<UserData> userSessions,
                                @MapsTo("questions") List<Question> questions,
                                @MapsTo("moderator") boolean moderator) {
    this.authenticated = authenticated;
    this.message = message;
    this.userData = userData;
    this.userSessions = Collections.unmodifiableList(new ArrayList<UserData>(userSessions));
    this.questions = Collections.unmodifiableList(new ArrayList<Question>(questions));
    this.moderator = moderator;
  }

  public boolean isAuthenticated() {
    return authenticated;
  }

  public String getMessage() {
    return message;
  }

  public UserData getUserData() {
    return userData;
  }

  public List<UserData> getUserSessions() {
    return userSessions;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public boolean isModerator() {
    return moderator;
  }
}

