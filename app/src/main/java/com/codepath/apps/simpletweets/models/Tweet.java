package com.codepath.apps.simpletweets.models;


import com.codepath.apps.simpletweets.BR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

// Parse JSON, Pass data, Encapsulate state logic
@Parcel
public class Tweet extends BaseObservable {
    public String body;
    public long uid;
    public int retweetCount;
    public int favoriteCount;
    public User user;  // embedded User object
    public String createdAt;
    public ArrayList<String> userMentioned = new ArrayList<>();
    public String inReplyToStatusId;
    public boolean favorited;
    public boolean retweeted;

    public Tweet() {
        user = new User();
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    @Bindable
    public int getFavoriteCount() {
        return favoriteCount;
    }

    public ArrayList<String> getUserMentioned() {
        return userMentioned;
    }

    public String getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    @Bindable
    public boolean isFavorited() {
        return favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
        if (favorited) {
            favoriteCount++;
        } else {
            favoriteCount--;
        }
        notifyPropertyChanged(BR.favoriteCount);
        notifyPropertyChanged(BR.favorited);
    }

    // Deserialize JSONObject and build Tweet object
    // Twitter.fromJSONObject("{...}") => <Tweet>
    public static Tweet fromJSONObject(JSONObject jsonObject) {
        Tweet tweet = new Tweet();

        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.retweetCount = jsonObject.getInt("retweet_count");
            tweet.favoriteCount = jsonObject.getInt("favorite_count");
            tweet.user = User.fromJSONObject(jsonObject.getJSONObject("user"));
            // Get hashtags
            JSONArray mentions = jsonObject.getJSONObject("entities").getJSONArray("user_mentions");
            for (int i = 0; i < mentions.length(); i++) {
                tweet.userMentioned.add(mentions.getJSONObject(i).getString("screen_name"));
            }
            // Get in_reply_to_status_id
            tweet.inReplyToStatusId = jsonObject.getString("in_reply_to_status_id_str");
            tweet.favorited = jsonObject.getBoolean("favorited");
            tweet.retweeted = jsonObject.getBoolean("retweeted");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    // Deserialize JSONArray and build Tweet objects
    // Twitter.fromJSONArray("{...}") => ArrayList<Tweet>
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Tweet tweet = fromJSONObject(jsonObject);
                if (tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return tweets;
    }
}
