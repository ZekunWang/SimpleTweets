package com.codepath.apps.simpletweets.models;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.activities.DetailsActivity;
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

    @BindView(R.id.ivProfile) ImageView ivProfile;
    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.tvRetweetCount) TextView tvRetweetCount;
    @BindView(R.id.tvFavoriteCount) TextView tvFavoriteCount;
    @BindView(R.id.tvScreenName) TextView tvScreenName;
    @BindView(R.id.tvRelativeTime) TextView tvRelativeTime;
    @BindView(R.id.tvBody) TextView tvBody;
    @BindView(R.id.ivFavorite) ImageView ivFavorite;
    @BindView(R.id.ivReply) ImageView ivReply;
    @BindView(R.id.ivRetweet) ImageView ivRetweet;
    ViewDataBinding binding;

    public ViewHolder(ViewDataBinding binding) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(binding.getRoot());
        ButterKnife.bind(this, binding.getRoot());
        this.binding = binding;
    }

    public ImageView getIvProfile() {
        return ivProfile;
    }

    public TextView getTvUsername() {
        return tvUsername;
    }

    public TextView getTvScreenName() {
        return tvScreenName;
    }

    public TextView getTvRelativeTime() {
        return tvRelativeTime;
    }

    public TextView getTvBody() {
        return tvBody;
    }

    public TextView getTvRetweetCount() {
        return tvRetweetCount;
    }

    public TextView getTvFavoriteCount() {
        return tvFavoriteCount;
    }

    public ImageView getIvFavorite() {
        return ivFavorite;
    }

    public ImageView getIvReply() {
        return ivReply;
    }

    public ImageView getIvRetweet() {
        return ivRetweet;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
