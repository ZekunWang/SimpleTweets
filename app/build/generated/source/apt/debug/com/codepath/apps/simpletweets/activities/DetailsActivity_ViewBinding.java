// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.simpletweets.activities;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.codepath.apps.simpletweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class DetailsActivity_ViewBinding<T extends DetailsActivity> implements Unbinder {
  protected T target;

  public DetailsActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.toolbar = finder.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.ivProfile = finder.findRequiredViewAsType(source, R.id.ivProfile, "field 'ivProfile'", ImageView.class);
    target.tvUsername = finder.findRequiredViewAsType(source, R.id.tvUsername, "field 'tvUsername'", TextView.class);
    target.tvScreenName = finder.findRequiredViewAsType(source, R.id.tvScreenName, "field 'tvScreenName'", TextView.class);
    target.tvBody = finder.findRequiredViewAsType(source, R.id.tvBody, "field 'tvBody'", TextView.class);
    target.tvAbsoluteTime = finder.findRequiredViewAsType(source, R.id.tvAbsoluteTime, "field 'tvAbsoluteTime'", TextView.class);
    target.tvRetweetCount = finder.findRequiredViewAsType(source, R.id.tvRetweetCount, "field 'tvRetweetCount'", TextView.class);
    target.tvFavoriteCount = finder.findRequiredViewAsType(source, R.id.tvFavoriteCount, "field 'tvFavoriteCount'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbar = null;
    target.ivProfile = null;
    target.tvUsername = null;
    target.tvScreenName = null;
    target.tvBody = null;
    target.tvAbsoluteTime = null;
    target.tvRetweetCount = null;
    target.tvFavoriteCount = null;

    this.target = null;
  }
}
