package org.jboss.qpanel.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.qpanel.client.shared.ModeratorService;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

/**
 * @author Mike Brock
 */
@Templated
public class QuestionForm extends Composite {
  @Inject @DataField TextArea questionText;
  @Inject @DataField Button askQuestion;
  @Inject @DataField Button cancel;

  @Inject RootPanel rootPanel;

  @Inject Caller<ModeratorService> moderatorService;

  @Inject IOCBeanManager beanManager;

  @EventHandler("askQuestion")
  public void submitQuestion(ClickEvent event) {
    moderatorService.call(new RemoteCallback<Void>() {
      @Override
      public void callback(Void response) {
      }
    }).submitQuestion(questionText.getValue());

    cancelClick(event);
  }

  @EventHandler("cancel")
  public void cancelClick(ClickEvent event) {
    beanManager.destroyBean(this);
  }

  @PreDestroy
  private void cleanup() {
    rootPanel.remove(this);
  }
}
