// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.simpletweets.activities;

import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.codepath.apps.simpletweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ComposeActivity_ViewBinding<T extends ComposeActivity> implements Unbinder {
  protected T target;

  public ComposeActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.toolbar = finder.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.etContent = finder.findRequiredViewAsType(source, R.id.etContent, "field 'etContent'", EditText.class);
    target.btnCompose = finder.findRequiredViewAsType(source, R.id.btnCompose, "field 'btnCompose'", Button.class);
    target.tvAvailableChars = finder.findRequiredViewAsType(source, R.id.tvAvailableChars, "field 'tvAvailableChars'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbar = null;
    target.etContent = null;
    target.btnCompose = null;
    target.tvAvailableChars = null;

    this.target = null;
  }
}
