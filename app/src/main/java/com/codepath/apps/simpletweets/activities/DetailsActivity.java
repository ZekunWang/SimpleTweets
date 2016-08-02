package com.codepath.apps.simpletweets.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.databinding.ActivityDetailsBinding;
import com.codepath.apps.simpletweets.models.Tweet;
import com.codepath.apps.simpletweets.others.PatternEditableBuilder;

import org.parceler.Parcels;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.ivProfile) ImageView ivProfile;
    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.tvScreenName) TextView tvScreenName;
    @BindView(R.id.tvBody) TextView tvBody;
    @BindView(R.id.tvAbsoluteTime) TextView tvAbsoluteTime;
    @BindView(R.id.tvRetweetCount) TextView tvRetweetCount;
    @BindView(R.id.tvFavoriteCount) TextView tvFavoriteCount;
    // Store the binding
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // Inflate the content view (replacing `setContentView`)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        // Get intent data
        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        // Bind data with layout
        binding.setTweet(tweet);

        // Bind views
        ButterKnife.bind(this);

        // Set Toolbar as Actionbar
        setSupportActionBar(toolbar);

        tvBody.setText(tweet.getBody());

        // Search for @ and #
        int color = ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary);
        new PatternEditableBuilder().
            addPattern(Pattern.compile("\\@(\\w+)"), color,
                new PatternEditableBuilder.SpannableClickedListener() {
                    @Override
                    public void onSpanClicked(String text) {
                        Toast.makeText(getApplicationContext(), "Clicked username: " + text,
                            Toast.LENGTH_SHORT).show();
                    }
                }).
            addPattern(Pattern.compile("\\#(\\w+)"), color,
                new PatternEditableBuilder.SpannableClickedListener() {
                    @Override
                    public void onSpanClicked(String text) {
                        Toast.makeText(getApplicationContext(), "Clicked hashtag: " + text,
                            Toast.LENGTH_SHORT).show();
                    }
                }).into(tvBody);

        // Load profile image for clicked tweet
    /*    Glide.with(this)
            .load(tweet.getUser().getProfileImageUrl())
            .placeholder(R.drawable.ic_launcher)
            .bitmapTransform(new RoundedCornersTransformation(this, 5, 0))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivProfile);
    */

    }

}
