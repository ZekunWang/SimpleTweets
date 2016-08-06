package com.codepath.apps.simpletweets.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.databinding.ActivityDetailsBinding;
import com.codepath.apps.simpletweets.fragments.ComposeDialogFragment;
import com.codepath.apps.simpletweets.models.Medium;
import com.codepath.apps.simpletweets.models.Tweet;
import com.codepath.apps.simpletweets.others.HelperMethods;
import com.codepath.apps.simpletweets.others.PatternEditableBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTouch;
import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailsActivity extends AppCompatActivity
        implements ComposeDialogFragment.ComposeDialogListener{

    public static final int REQUEST_REPLY = 21;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.ivProfile) ImageView ivProfile;
    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.tvScreenName) TextView tvScreenName;
    @BindView(R.id.tvBody) TextView tvBody;
    @BindView(R.id.tvAbsoluteTime) TextView tvAbsoluteTime;
    @BindView(R.id.tvRetweetCount) TextView tvRetweetCount;
    @BindView(R.id.tvFavoriteCount) TextView tvFavoriteCount;
    @BindView(R.id.etReply) EditText etReply;
    @BindView(R.id.btnCompose) Button btnCompose;
    @BindView(R.id.tvAvailableChars) TextView tvAvailableChars;
    @BindView(R.id.ivFavorite) ImageView ivFavorite;
    @BindView(R.id.ivMedia) ImageView ivMedia;
    // Store the binding
    private ActivityDetailsBinding binding;
    Tweet tweet;
    TwitterClient client;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // Inflate the content view (replacing `setContentView`)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        // Get intent data
        tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        position = getIntent().getIntExtra("position", 0);
        // Bind data with layout
        binding.setTweet(tweet);
        // Bind views
        ButterKnife.bind(this);

        // Set Toolbar as Actionbar
        setSupportActionBar(toolbar);

        // Get the client
        client = TwitterApplication.getRestClient();    // singleton client

        tvBody.setText(tweet.getBody());

        // Inflate media
        List<Medium> media = tweet.getMedia();
        if (media != null) {
            if (media.size() == 1) {
                ivMedia.setVisibility(View.VISIBLE);
                Glide.with(this)
                    .load(media.get(0).getUrl())
                    .fitCenter()
                    .into(ivMedia);
            }
        }

        // Search for @ and #
        HelperMethods.formatBody(getApplicationContext(), tvBody);
        // Set text change listener
        HelperMethods.setTextChangedListener(this, etReply, tvAvailableChars, btnCompose);
    }

    @OnFocusChange(R.id.etReply)
    public void onFocusChange(View view, boolean b) {
        if (!b) {   // not focus now
            if (etReply.length() == 0) {
                btnCompose.setVisibility(View.GONE);
                tvAvailableChars.setVisibility(View.GONE);
            }
        } else {    // focus now
            btnCompose.setVisibility(View.VISIBLE);
            tvAvailableChars.setVisibility(View.VISIBLE);

            if (etReply.length() == 0) {
                // @ status user screen name
                StringBuilder sb = new StringBuilder("@")
                    .append(tweet.getUser().getScreenName())
                    .append(' ');
                // @ all mentioned user
                for (String screenName : tweet.getUserMentions()) {
                    if (!screenName.equals(tweet.getUser().getScreenName())) {
                        sb.append("@").append(screenName).append(' ');
                    }
                }

                etReply.setText(sb.toString());

                // Move cursor to end of reply EditText
                etReply.setSelection(sb.length());

                // Set initial remaining char count
                tvAvailableChars.setText(String.valueOf(140 - etReply.length()));
            }
        }
    }

    // Listener to expand icon
    @OnTouch(R.id.etReply)
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(event.getRawX() >= (etReply.getRight() - etReply.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                // Expand reply triggered
                jumpToReply();
                return true;
            }
        }
        return false;
    }

    private void jumpToReply() {
        ComposeDialogFragment composeDialogFragment = ComposeDialogFragment.newInstance(REQUEST_REPLY, tweet);
        composeDialogFragment.show(getFragmentManager(), "fragment_compose");
    }

    @Override
    public void onFinishComposeDialog(int requestCode, Tweet t) {
        if (requestCode == REQUEST_REPLY) {
            HelperMethods.postTweet(t);
        }
    }

    private void clearReply() {
        // Clear reply EditText if not empty
        etReply.setText("");
        etReply.clearFocus();
        btnCompose.setVisibility(View.GONE);
        tvAvailableChars.setVisibility(View.GONE);
    }

    @OnClick({R.id.ivReply, R.id.ivFavorite})
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ivReply:
                jumpToReply();
                break;
            case R.id.ivFavorite:
                // Switch favorite
                HelperMethods.switchFavorite(tweet, ivFavorite, tvFavoriteCount);
                break;
        }
    }

    // Clear focus on EditText when click outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }


    // Submit new Tweet
    public void onSubmit(View v) {
        if (etReply.getText().length() <= 0) {    // No input
            return;
        }

        Tweet newTweet = new Tweet();
        newTweet.body = etReply.getText().toString();
        newTweet.user = TimelineActivity.ACCOUNT;
        newTweet.inReplyToStatusId = tweet.getInReplyToStatusId();

        // Post new Tweet reply
        HelperMethods.postTweet(newTweet);
        // Clear EditText
        clearReply();
    }

    @Override
    public void onBackPressed()
    {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("position", position);
        // Pass relevant data back as a result
        data.putExtra("tweet", Parcels.wrap(tweet));
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
