package com.codepath.apps.simpletweets.others;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.activities.TwitterApplication;
import com.codepath.apps.simpletweets.activities.TwitterClient;
import com.codepath.apps.simpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zwang_000 on 7/24/2016.
 */
public class HelperMethods {

    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static void setNotificationBarColor(Context context, int id) {
        Window window = ((Activity) context).getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(HelperMethods.getColor(context, id));
    }

    public static double dpToPx(Context context, double dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        double px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static double pxToDp(Context context, double px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        double dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static boolean isConnected(Context context) {
        if (!HelperMethods.isNetworkAvailable(context) || !HelperMethods.isOnline()) {
            Toast.makeText(context, "Internet Unavailable...", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
            = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void hideSoftKeyboard(Context context) {
        ((Activity)context).getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public static void postTweet(Tweet tweet) {
        TwitterApplication.getRestClient().composeTweet(tweet, new JsonHttpResponseHandler(){
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                Tweet newTweet = Tweet.fromJSONObject(jsonObject);
                if (newTweet != null) {
                    // Clear EditText
                }
            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONObject errorResponse) {
                if (errorResponse != null) {
                    Log.d("DEBUG", "Post Tweet Comment Error: " + errorResponse.toString());
                }
            }
        });
    }

    public static void formatBody(final Context context, TextView tvBody) {// Search for @ and #
        int color = ContextCompat.getColor(context, R.color.primary);
        new PatternEditableBuilder()
            .addPattern(Pattern.compile("\\@(\\w+)"), color,
                new PatternEditableBuilder.SpannableClickedListener() {
                    @Override
                    public void onSpanClicked(String text) {
                        Toast.makeText(context, "Mentioned: " + text,
                            Toast.LENGTH_SHORT).show();
                    }
                })
            .addPattern(Pattern.compile("\\#(\\w+)"), color,
                new PatternEditableBuilder.SpannableClickedListener() {
                    @Override
                    public void onSpanClicked(String text) {
                        Toast.makeText(context, "Hashtagged: " + text,
                            Toast.LENGTH_SHORT).show();
                    }
                })
            .into(tvBody);
    }

    public static void setTextChangedListener(final Context context, EditText etContent,
            final TextView tvAvailableChars, final Button btnCompose) {
        etContent.addTextChangedListener(new TextWatcher() {
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
                    color = ContextCompat.getColor(context, R.color.primary);
                    btnCompose.setTextColor(color);
                    // Set disable background for button
                    btnCompose.setBackgroundResource(R.drawable.button_background_disabled);
                    // Disable clickable
                    btnCompose.setClickable(false);
                } else {
                    // Set normal color for count text
                    color = ContextCompat.getColor(context, R.color.item_text_content);
                    tvAvailableChars.setTextColor(color);
                    // Set disable button text color
                    color = ContextCompat.getColor(context, R.color.icons);
                    btnCompose.setTextColor(color);
                    // Set regular background for button
                    btnCompose.setBackgroundResource(R.drawable.button_background);
                    // Enable clickable
                    btnCompose.setClickable(true);
                }
            }
        });
    }

    public static void switchFavorite(final Tweet tweet, final ImageView ivFavorite, final TextView tvFavoriteCount) {
        TwitterClient client = TwitterApplication.getRestClient();
        if (tweet.isFavorited()) {
            client.unSetFavorite(tweet, new JsonHttpResponseHandler(){
                // SUCCESS
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                    Tweet newTweet = Tweet.fromJSONObject(jsonObject);
                    if (newTweet != null) {
                        // Reverse favorited status
                        tweet.setFavorited(false);
                        // Switch icon
                        ivFavorite.setImageResource(R.drawable.ic_heart);
                        // Update favoriteCount
                        tvFavoriteCount.setText("" + tweet.getFavoriteCount());
                    }
                }

                // FAILURE
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                    JSONObject errorResponse) {
                    if (errorResponse != null) {
                        Log.d("DEBUG", "Unset Favorite Error: " + errorResponse.toString());
                    }
                }
            });
        } else {
            client.setFavorite(tweet, new JsonHttpResponseHandler(){
                // SUCCESS
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                    Tweet newTweet = Tweet.fromJSONObject(jsonObject);
                    if (newTweet != null) {
                        // Reverse favorited status
                        tweet.setFavorited(true);
                        // Switch icon
                        ivFavorite.setImageResource(R.drawable.ic_heart_lighted);
                        // Update favoriteCount
                        tvFavoriteCount.setText("" + tweet.getFavoriteCount());
                    }
                }

                // FAILURE
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                    JSONObject errorResponse) {
                    if (errorResponse != null) {
                        Log.d("DEBUG", "Set Favorite Error: " + errorResponse.toString());
                    }
                }
            });
        }
    }
}
