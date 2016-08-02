package com.codepath.apps.simpletweets.adapters;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codepath.apps.simpletweets.R;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class BindingAdapterUtils {
    @BindingAdapter({"bind:profileImageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
            .load(url)
            .placeholder(R.drawable.ic_launcher)
            .bitmapTransform(new RoundedCornersTransformation(view.getContext(), 5, 0))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view);
    }
}
