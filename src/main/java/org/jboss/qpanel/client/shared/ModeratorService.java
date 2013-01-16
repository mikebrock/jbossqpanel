package org.jboss.qpanel.client.shared;

import org.jboss.errai.bus.server.annotations.Remote;

/**
 * @author Mike Brock
 */
@Remote
public interface ModeratorService {
  public AuthenticationResponse load();

  public AuthenticationResponse authenticate(String email, String name);

  public void submitQuestion(String questionText);

  public void voteFor(int id);

  public void markAnswered(int id);

  public void deleteQuestion(int id);
}
