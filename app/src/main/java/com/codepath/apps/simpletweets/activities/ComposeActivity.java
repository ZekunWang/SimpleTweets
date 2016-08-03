package com.codepath.apps.simpletweets.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.models.Tweet;
import com.codepath.apps.simpletweets.models.User;

import org.json.JSONArray;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComposeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.etContent) EditText etContent;
    @BindView(R.id.btnCompose) Button btnCompose;
    @BindView(R.id.tvAvailableChars) TextView tvAvailableChars;
    Tweet tweet;
    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        requestCode = getIntent().getIntExtra("request_code", 0);

        // Put hashtags before input
        if (requestCode == DetailsActivity.REQUEST_REPLY) {
            tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
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

            etContent.setText(sb.toString());

            // Move cursor to end of reply EditText
            etContent.setSelection(sb.length());

            // Set initial remaining char count
            tvAvailableChars.setText(String.valueOf(140 - etContent.length()));
        }

        // Listen to compost characters
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

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

    // Submit new Tweet
    public void onSubmit(View v) {
        if (etContent.getText().length() <= 0) {    // No input
            finish();
        }

        Tweet newTweet = new Tweet();
        newTweet.body = etContent.getText().toString();
        newTweet.user = TimelineActivity.ACCOUNT;
        if (tweet != null && requestCode == DetailsActivity.REQUEST_REPLY) {
            newTweet.inReplyToStatusId = String.valueOf(tweet.getUid());
        }
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("tweet", Parcels.wrap(newTweet));
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
