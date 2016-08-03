package com.codepath.apps.simpletweets.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTouch;
import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailsActivity extends AppCompatActivity {

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

        etReply.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                int remain = 140 - editable.length(), color = 0;
                tvAvailableChars.setText("" + remain);
                if (remain < 0) {
                    // Set alarm color for count text
                    tvAvailableChars.setTextColor(Color.RED);
                    // Set disable button text color
                    color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
                    btnCompose.setTextColor(color);
                    // Set disable background for button
                    btnCompose.setBackgroundResource(R.drawable.button_background_disabled);
                    // Disable clickable
                    btnCompose.setClickable(false);
                } else {
                    // Set normal color for count text
                    color = ContextCompat.getColor(getApplicationContext(), R.color.item_text_content);
                    tvAvailableChars.setTextColor(color);
                    // Set disable button text color
                    color = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
                    btnCompose.setTextColor(color);
                    // Set regular background for button
                    btnCompose.setBackgroundResource(R.drawable.button_background);
                    // Enable clickable
                    btnCompose.setClickable(true);
                }
            }
        });
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
                for (String screenName : tweet.getUserMentioned()) {
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
        Intent intent = new Intent(getApplicationContext(), ComposeActivity.class);
        intent.putExtra("tweet", Parcels.wrap(tweet));
        intent.putExtra("request_code", REQUEST_REPLY);
        startActivityForResult(intent, REQUEST_REPLY);
    }

    private void clearReply() {
        // Clear reply EditText if not empty
        etReply.setText("");
        etReply.clearFocus();
    }

    @OnClick({R.id.ivReply, R.id.ivFavorite})
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ivReply:
                jumpToReply();
                break;
            case R.id.ivFavorite:
                if (tweet.isFavorited()) {
                    client.unSetFavorite(tweet, new JsonHttpResponseHandler(){
                        // SUCCESS
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                            Tweet newTweet = Tweet.fromJSONObject(jsonObject);
                            if (newTweet != null) {
                                tweet.favorited = false;
                                ivFavorite.setImageResource(R.drawable.ic_heart);
                            }
                        }

                        // FAILURE
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                            JSONObject errorResponse) {
                            Log.d("DEBUG", "Unset Favorite Error: " + errorResponse.toString());
                        }
                    });
                } else {
                    client.setFavorite(tweet, new JsonHttpResponseHandler(){
                        // SUCCESS
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                            Tweet newTweet = Tweet.fromJSONObject(jsonObject);
                            if (newTweet != null) {
                                tweet.favorited = true;
                                ivFavorite.setImageResource(R.drawable.ic_heart_lighted);
                            }
                        }

                        // FAILURE
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                            JSONObject errorResponse) {
                            Log.d("DEBUG", "Set Favorite Error: " + errorResponse.toString());
                        }
                    });
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_COMPOSE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_REPLY) {
            Tweet tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            postTweet(tweet);
        }
    }

    private void postTweet(Tweet tweet) {
        client.composeTweet(tweet, new JsonHttpResponseHandler(){
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                Tweet newTweet = Tweet.fromJSONObject(jsonObject);
                if (newTweet != null) {
                    // Clear EditText
                    clearReply();
                }
            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONObject errorResponse) {
                Log.d("DEBUG", "Post Tweet Comment Error: " + errorResponse.toString());
            }
        });
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
        postTweet(newTweet);
    }

    @Override
    public void onBackPressed()
    {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("position", position);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
