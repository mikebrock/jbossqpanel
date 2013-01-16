package org.jboss.qpanel.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

import java.util.Date;

/**
 * @author Mike Brock
 */
@Portable
@Bindable
public class Question implements Comparable<Question> {
  private int id;
  private Date date;
  private UserData user;
  private String questionText;
  private boolean answered;
  private int votes = 1;

  public Question() {
  }

  public Question(int id, Date date, UserData user, String questionText, boolean answered) {
    this.id = id;
    this.date = date;
    this.user = user;
    this.questionText = questionText;
    this.answered = answered;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public UserData getUser() {
    return user;
  }

  public void setUser(UserData user) {
    this.user = user;
  }

  public String getQuestionText() {
    return questionText;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public boolean isAnswered() {
    return answered;
  }

  public void setAnswered(boolean answered) {
    this.answered = answered;
  }

  public int getVotes() {
    return votes;
  }

  public void setVotes(int votes) {
    this.votes = votes;
  }

  @Override
  public int compareTo(Question o) {
    return answered ? 1 : (o.getVotes() - getVotes());
  }

  @Override
  public String toString() {
    return "Question{" +
        "date=" + date +
        ", user=" + user +
        ", questionText='" + questionText + '\'' +
        ", answered=" + answered +
        '}';
  }
}
