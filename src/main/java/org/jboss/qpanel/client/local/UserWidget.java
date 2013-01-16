package org.jboss.qpanel.client.local;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.qpanel.client.shared.UserData;

import javax.inject.Inject;

/**
 * @author Mike Brock
 */
@Templated
  public class UserWidget extends Composite implements HasModel<UserData> {
  @Inject @AutoBound DataBinder<UserData> dataBinder;
  @Inject @Bound @DataField Label name;

  @Override
  public UserData getModel() {
    return dataBinder.getModel();
  }

  @Override
  public void setModel(final UserData model) {
    dataBinder.setModel(model, InitialState.FROM_MODEL);
  }
}
