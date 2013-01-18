package org.jboss.qpanel.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import javax.inject.Inject;

/**
 * @author Mike Brock
 */
@Templated
public class SignInPanel extends Composite {
  @Inject @DataField private TextBox email;
  @Inject @DataField private TextBox userName;
  @Inject @DataField private TextBox password;

  @Inject @DataField private Button joinButton;

  @Inject RootPanel rootPanel;

  @Inject SessionControl sessionControl;

  @EventHandler("joinButton")
  private void onJoinClick(ClickEvent event) {
    event.preventDefault();
    event.stopPropagation();

    sessionControl.authenticate(email.getValue(), userName.getValue(), password.getValue());

    rootPanel.remove(this);
  }
}
