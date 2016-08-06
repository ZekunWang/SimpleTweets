package com.codepath.apps.simpletweets.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Table(name = "media")
@Parcel(analyze={Medium.class})   // add Parceler to ignore Model
public class Medium extends Model {

    @Column(name="type")
    public String type;
    @Column(name="url")
    public String url;
    @Column(name="tweet", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Tweet tweet;

    public Medium() {
        super();
    }

    public String getType() {
        return type;
    }

    public String getUrlSmall() {
        return url + ":small";
    }

    public String getUrl() {
        return url;
    }


    // Finds existing user based on remoteId or creates new user and returns
    public static Medium findOrCreateFromJson(JSONObject json, Tweet tweet) {
        String u = null; // get just the remote id
        Medium medium = null;

        try {
            u = json.getString("media_url");
            // Search for duplicate
            medium = new Select().from(Medium.class).where("tweet = ?", tweet.getId()).executeSingle();
            if (medium == null) {
                // create and return new user
                medium = Medium.fromJSONObject(json, tweet);
                medium.save();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return medium;
    }

    public static Medium fromJSONObject(JSONObject jsonObject, Tweet tweet) {
        Medium medium = new Medium();
        try {
            medium.type = jsonObject.getString("type");
            medium.url = jsonObject.getString("media_url");
            medium.tweet = tweet;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return medium;
    }

    public static List<Medium> fromJSONArray(JSONArray jsonArray, Tweet tweet) {
        List<Medium> media = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Medium m = findOrCreateFromJson(jsonArray.getJSONObject(i), tweet);
                if (m != null) {
                    media.add(m);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return media;
    }
}
