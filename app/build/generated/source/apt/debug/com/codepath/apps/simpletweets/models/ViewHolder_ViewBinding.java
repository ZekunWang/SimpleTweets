// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.simpletweets.models;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.codepath.apps.simpletweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ViewHolder_ViewBinding<T extends ViewHolder> implements Unbinder {
  protected T target;

  public ViewHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.ivProfile = finder.findRequiredViewAsType(source, R.id.ivProfile, "field 'ivProfile'", ImageView.class);
    target.tvUsername = finder.findRequiredViewAsType(source, R.id.tvUsername, "field 'tvUsername'", TextView.class);
    target.tvRetweetCount = finder.findRequiredViewAsType(source, R.id.tvRetweetCount, "field 'tvRetweetCount'", TextView.class);
    target.tvFavoriteCount = finder.findRequiredViewAsType(source, R.id.tvFavoriteCount, "field 'tvFavoriteCount'", TextView.class);
    target.tvScreenName = finder.findRequiredViewAsType(source, R.id.tvScreenName, "field 'tvScreenName'", TextView.class);
    target.tvRelativeTime = finder.findRequiredViewAsType(source, R.id.tvRelativeTime, "field 'tvRelativeTime'", TextView.class);
    target.tvBody = finder.findRequiredViewAsType(source, R.id.tvBody, "field 'tvBody'", TextView.class);
    target.ivFavorite = finder.findRequiredViewAsType(source, R.id.ivFavorite, "field 'ivFavorite'", ImageView.class);
    target.ivReply = finder.findRequiredViewAsType(source, R.id.ivReply, "field 'ivReply'", ImageView.class);
    target.ivRetweet = finder.findRequiredViewAsType(source, R.id.ivRetweet, "field 'ivRetweet'", ImageView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivProfile = null;
    target.tvUsername = null;
    target.tvRetweetCount = null;
    target.tvFavoriteCount = null;
    target.tvScreenName = null;
    target.tvRelativeTime = null;
    target.tvBody = null;
    target.ivFavorite = null;
    target.ivReply = null;
    target.ivRetweet = null;

    this.target = null;
  }
}
