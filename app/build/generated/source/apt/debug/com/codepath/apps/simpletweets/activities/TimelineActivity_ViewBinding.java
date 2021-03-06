// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.simpletweets.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.codepath.apps.simpletweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TimelineActivity_ViewBinding<T extends TimelineActivity> implements Unbinder {
  protected T target;

  private View view2131427465;

  private View view2131427469;

  public TimelineActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.toolbar, "field 'toolbar' and method 'onClick'");
    target.toolbar = finder.castView(view, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view2131427465 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rvTweets = finder.findRequiredViewAsType(source, R.id.rvTweets, "field 'rvTweets'", RecyclerView.class);
    target.swipeContainer = finder.findRequiredViewAsType(source, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
    view = finder.findRequiredView(source, R.id.fab, "field 'fab' and method 'onClick'");
    target.fab = finder.castView(view, R.id.fab, "field 'fab'", FloatingActionButton.class);
    view2131427469 = view;
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
    target.rvTweets = null;
    target.swipeContainer = null;
    target.fab = null;

    view2131427465.setOnClickListener(null);
    view2131427465 = null;
    view2131427469.setOnClickListener(null);
    view2131427469 = null;

    this.target = null;
  }
}
