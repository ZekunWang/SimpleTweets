package com.codepath.apps.simpletweets.models;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.activities.DetailsActivity;
import com.codepath.apps.simpletweets.databinding.ItemTweetBinding;
import com.codepath.apps.simpletweets.fragments.ComposeDialogFragment;
import com.codepath.apps.simpletweets.others.HelperMethods;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class ViewHolder extends RecyclerView.ViewHolder {

    ItemTweetBinding binding;

    public ViewHolder(ItemTweetBinding binding) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(binding.getRoot());
        this.binding = binding;
    }

    public ItemTweetBinding getBinding() {
        return binding;
    }
}
