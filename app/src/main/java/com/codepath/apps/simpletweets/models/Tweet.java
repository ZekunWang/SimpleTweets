package com.codepath.apps.simpletweets.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import android.util.Log;

import java.util.ArrayList;

// Parse JSON, Pass data, Encapsulate state logic
@Parcel
public class Tweet {
    public String body;
    public long uid;
    public int retweetCount;
    public int favoriteCount;
    public User user;  // embedded User object
    public String createdAt;

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

    public int getFavoriteCount() {
        return favoriteCount;
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
                Log.i("DEBUG", jsonObject.toString());
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
