// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.simpletweets.activities;

import android.view.MotionEvent;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.codepath.apps.simpletweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class DetailsActivity_ViewBinding<T extends DetailsActivity> implements Unbinder {
  protected T target;

  private View view2131427463;

  private View view2131427465;

  private View view2131427464;

  private View view2131427466;

  private View view2131427447;

  public DetailsActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.ivReply, "method 'onClick'");
    view2131427463 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.ivFavorite, "method 'onClick'");
    view2131427465 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.ivRetweet, "method 'onClick'");
    view2131427464 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.ivShare, "method 'onClick'");
    view2131427466 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.etReply, "method 'onFocusChange' and method 'onTouch'");
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
  }

  @Override
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131427463.setOnClickListener(null);
    view2131427463 = null;
    view2131427465.setOnClickListener(null);
    view2131427465 = null;
    view2131427464.setOnClickListener(null);
    view2131427464 = null;
    view2131427466.setOnClickListener(null);
    view2131427466 = null;
    view2131427447.setOnFocusChangeListener(null);
    view2131427447.setOnTouchListener(null);
    view2131427447 = null;

    this.target = null;
  }
}
