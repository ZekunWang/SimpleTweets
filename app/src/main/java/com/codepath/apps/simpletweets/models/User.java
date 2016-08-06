package com.codepath.apps.simpletweets.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Table(name = "users")
@Parcel(analyze={User.class})   // add Parceler to ignore Model
public class User extends Model {

    @Column(name = "name")
    public String name;
    @Column(name = "uid", unique = true)
    public long uid;
    @Column(name = "screen_name")
    public String screenName;
    @Column(name = "profile_image_url")
    public String profileImageUrl;

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    // Finds existing user based on remoteId or creates new user and returns
    public static User findOrCreateFromJson(JSONObject json) {
        long rId = 0; // get just the remote id
        User user = null;

        try {
            rId = json.getLong("id");
            // Search for duplicate
            user = new Select().from(User.class).where("uid = ?", rId).executeSingle();
            if (user == null) {
                // create and return new user
                user = User.fromJSONObject(json);
                user.save();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    /// Deserialize JSON => <User>
    public static User fromJSONObject(JSONObject jsonObject) {
        User user = new User();

        try {
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
