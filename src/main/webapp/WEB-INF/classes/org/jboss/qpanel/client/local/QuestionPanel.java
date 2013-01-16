package org.jboss.qpanel.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.qpanel.client.shared.Deleted;
import org.jboss.qpanel.client.shared.MarkAnswered;
import org.jboss.qpanel.client.shared.NewQuestion;
import org.jboss.qpanel.client.shared.Question;
import org.jboss.qpanel.client.shared.UserData;
import org.jboss.qpanel.client.shared.UserJoin;
import org.jboss.qpanel.client.shared.VoteEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * @author Mike Brock
 */
@Templated
public class QuestionPanel extends Composite {
  @Inject @DataField private Label participants;
  @Inject @DataField private Button sessionButton;
  @Inject @DataField private Button askQuestion;

  @Inject @DataField ListWidget<UserData, UserWidget> userList;
  @Inject @DataField ListWidget<Question, QuestionWidget> questionList;

  @Inject private SignInPanel signInPanel;
  @Inject private RootPanel rootPanel;
  @Inject private SessionControl sessionControl;

  @Inject private IOCBeanManager beanManager;

  @PostConstruct
  private void onDone() {
    askQuestion.setVisible(false);
  }

  @EventHandler("sessionButton")
  private void joinClick(ClickEvent event) {
    if (!sessionControl.isAuthenticated()) {
      rootPanel.add(signInPanel);
    }
    else {
      sessionButton.setText("Join");
    }
  }

  @EventHandler("askQuestion")
  private void askQuestionClick(ClickEvent event) {
    final QuestionForm instance = beanManager.lookupBean(QuestionForm.class).getInstance();
    rootPanel.add(instance);
  }

  private void observeLoad(@Observes LoadEvent loadEvent) {
    refreshUsers();
    refreshQuestions();
  }

  private void observeLoggedIn(@Observes LoggedIn loggedIn) {
    sessionButton.setVisible(false);
    askQuestion.setVisible(true);
    refreshUsers();
    refreshQuestions();
  }

  private void observeUserJoin(@Observes UserJoin userJoin) {
    sessionControl.getUserDataMap().put(userJoin.getUserData().getName(), userJoin.getUserData());
    refreshUsers();
  }

  private void observeNewQuestion(@Observes NewQuestion newQuestion) {
    sessionControl.addQuestion(newQuestion.getQuestion());
    refreshQuestions();
  }

  private void observeVoteEvent(@Observes VoteEvent voteEvent) {
    final Question question = sessionControl.getQuestionsMap().get(voteEvent.getId());
    if (question != null) {
      question.setVotes(voteEvent.getVoteScore());
    }
    refreshQuestions();
  }

  private void observeAnswered(@Observes MarkAnswered markAnswered) {
    final Question question = sessionControl.getQuestionsMap().remove(markAnswered.getId());
    if (question != null) {
      question.setAnswered(true);
    }
    refreshQuestions();
  }

  private void observesDeleted(@Observes Deleted deleted) {
    sessionControl.getQuestionsMap().remove(deleted.getId());
    refreshQuestions();
  }


  private void refreshUsers() {
    final ArrayList<UserData> items = new ArrayList<UserData>(sessionControl.getUserDataMap().values());
    userList.setItems(items);
    participants.setText(String.valueOf(items.size()));
  }

  private void refreshQuestions() {
    questionList.setItems(sessionControl.getQuestionList());
  }
}
