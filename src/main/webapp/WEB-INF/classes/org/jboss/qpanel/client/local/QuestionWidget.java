package org.jboss.qpanel.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.PropertyChangeHandler;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.qpanel.client.shared.ModeratorService;
import org.jboss.qpanel.client.shared.Question;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Mike Brock
 */
@Templated
public class QuestionWidget extends Composite implements HasModel<Question> {
  @Inject @AutoBound DataBinder<Question> dataBinder;
  @Inject @Bound(property = "user.name") @DataField Label name;
  @Inject @Bound(converter = DateConverter.class) @DataField Label date;
  @Inject @Bound @DataField Label votes;

  @Inject @DataField Anchor vote;

  @Inject @Bound @DataField Label questionText;

  @Inject @DataField Button answeredButton;
  @Inject @DataField Button deleteButton;

  @Inject Caller<ModeratorService> moderatorService;
  @Inject SessionControl sessionControl;

  @PostConstruct
  private void setup() {
    if (!sessionControl.isModerator()) {
      answeredButton.setVisible(false);
      deleteButton.setVisible(false);
    }

    getElement().getStyle().setOpacity(0.6);
  }

  @Override
  public Question getModel() {
    return dataBinder.getModel();
  }

  @Override
  public void setModel(Question model) {
    dataBinder.setModel(model, InitialState.FROM_MODEL);
    if (getModel().isAnswered()) {
      getElement().getStyle().setOpacity(0.2);
    }
    else {

      final int idx = sessionControl.getQuestionList().indexOf(model);
      switch (idx) {
        case 0:
          getElement().getStyle().setOpacity(1);
          break;
        case 1:
          getElement().getStyle().setOpacity(0.8);
          break;
        case 2:
          getElement().getStyle().setOpacity(0.6);
          break;
        default:
          getElement().getStyle().setOpacity(0.5);
      }
    }
  }

  @EventHandler("vote")
  private void voteForIssue(ClickEvent event) {
    moderatorService.call(new RemoteCallback<Void>() {
      @Override
      public void callback(Void response) {
      }
    }).voteFor(dataBinder.getModel().getId());
  }

  @EventHandler("answeredButton")
  private void answered(ClickEvent event) {
    moderatorService.call(new RemoteCallback<Void>() {
      @Override
      public void callback(Void response) {
      }
    }).markAnswered(dataBinder.getModel().getId());
  }

  @EventHandler("deleteButton")
  private void deleted(ClickEvent event) {
    moderatorService.call(new RemoteCallback<Void>() {
      @Override
      public void callback(Void response) {
      }
    }).deleteQuestion(dataBinder.getModel().getId());
  }
}
