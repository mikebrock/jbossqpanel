package org.jboss.qpanel.server;

import org.jboss.qpanel.client.shared.UserData;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mike Brock
 */
public class UserSession {
  private Date created;
  private String email;
  private String name;
  private Set<Integer> voteRecord = new HashSet<Integer>();
  private boolean moderator;

  public UserSession(String email, String name, boolean moderator) {
    this.created = new Date();
    this.email = email;
    this.name = name;
    this.moderator = moderator;
  }

  public Date getCreated() {
    return created;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public boolean isModerator() {
    return moderator;
  }

  public boolean hasVoted(int questionId) {
    return voteRecord.contains(questionId);
  }

  public void recordVote(int questionId) {
    voteRecord.add(questionId);
  }

  public UserData asUserData() {
    return new UserData(getName(), getEmail());
  }
}
