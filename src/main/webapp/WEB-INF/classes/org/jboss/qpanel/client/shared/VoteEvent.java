package org.jboss.qpanel.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.marshalling.client.api.annotations.MapsTo;

/**
 * @author Mike Brock
 */
@Portable
public class VoteEvent {
  private final int id;
  private final int voteScore;
  private final UserData userData;

  public VoteEvent(@MapsTo("id") int id,
                   @MapsTo("voteScore") int voteScore,
                   @MapsTo("userData") UserData userData) {
    this.id = id;
    this.voteScore = voteScore;
    this.userData = userData;
  }

  public int getId() {
    return id;
  }

  public int getVoteScore() {
    return voteScore;
  }

  public UserData getUserData() {
    return userData;
  }
}
