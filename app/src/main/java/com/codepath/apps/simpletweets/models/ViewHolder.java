package com.codepath.apps.simpletweets.models;

import com.codepath.apps.simpletweets.R;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivProfile) ImageView ivProfile;
    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.tvRetweetCount) TextView tvRetweetCount;
    @BindView(R.id.tvFavoriteCount) TextView tvFavoriteCount;
    @BindView(R.id.tvScreenName) TextView tvScreenName;
    @BindView(R.id.tvRelativeTime) TextView tvRelativeTime;
    @BindView(R.id.tvBody) TextView tvBody;

    public ViewHolder(View view) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(view);
        ButterKnife.bind(this, view);
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
}
