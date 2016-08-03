// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.simpletweets.activities;

import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.codepath.apps.simpletweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class DetailsActivity_ViewBinding<T extends DetailsActivity> implements Unbinder {
  protected T target;

  private View view2131427447;

  private View view2131427457;

  private View view2131427456;

  public DetailsActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.toolbar = finder.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.ivProfile = finder.findRequiredViewAsType(source, R.id.ivProfile, "field 'ivProfile'", ImageView.class);
    target.tvUsername = finder.findRequiredViewAsType(source, R.id.tvUsername, "field 'tvUsername'", TextView.class);
    target.tvScreenName = finder.findRequiredViewAsType(source, R.id.tvScreenName, "field 'tvScreenName'", TextView.class);
    target.tvBody = finder.findRequiredViewAsType(source, R.id.tvBody, "field 'tvBody'", TextView.class);
    target.tvAbsoluteTime = finder.findRequiredViewAsType(source, R.id.tvAbsoluteTime, "field 'tvAbsoluteTime'", TextView.class);
    target.tvRetweetCount = finder.findRequiredViewAsType(source, R.id.tvRetweetCount, "field 'tvRetweetCount'", TextView.class);
    target.tvFavoriteCount = finder.findRequiredViewAsType(source, R.id.tvFavoriteCount, "field 'tvFavoriteCount'", TextView.class);
    view = finder.findRequiredView(source, R.id.etReply, "field 'etReply', method 'onFocusChange', and method 'onTouch'");
    target.etReply = finder.castView(view, R.id.etReply, "field 'etReply'", EditText.class);
    view2131427447 = view;
    view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View p0, boolean p1) {
        target.onFocusChange(p0, p1);
      }
    });
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        return target.onTouch(p0, p1);
      }
    });
    target.btnCompose = finder.findRequiredViewAsType(source, R.id.btnCompose, "field 'btnCompose'", Button.class);
    target.tvAvailableChars = finder.findRequiredViewAsType(source, R.id.tvAvailableChars, "field 'tvAvailableChars'", TextView.class);
    view = finder.findRequiredView(source, R.id.ivFavorite, "field 'ivFavorite' and method 'onClick'");
    target.ivFavorite = finder.castView(view, R.id.ivFavorite, "field 'ivFavorite'", ImageView.class);
    view2131427457 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.ivReply, "method 'onClick'");
    view2131427456 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
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
    target.etReply = null;
    target.btnCompose = null;
    target.tvAvailableChars = null;
    target.ivFavorite = null;

    view2131427447.setOnFocusChangeListener(null);
    view2131427447.setOnTouchListener(null);
    view2131427447 = null;
    view2131427457.setOnClickListener(null);
    view2131427457 = null;
    view2131427456.setOnClickListener(null);
    view2131427456 = null;

    this.target = null;
  }
}
