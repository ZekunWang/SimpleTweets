package com.codepath.apps.simpletweets.databinding;
import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.BR;
import android.view.View;
public class ContentDetailsBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rlReply, 6);
        sViewsWithIds.put(R.id.ivProfile, 7);
        sViewsWithIds.put(R.id.tvBody, 8);
        sViewsWithIds.put(R.id.tvAbsoluteTime, 9);
        sViewsWithIds.put(R.id.tvRetweet, 10);
    }
    // views
    public final android.widget.EditText etReply;
    public final android.widget.ImageView ivProfile;
    private final android.widget.RelativeLayout mboundView0;
    public final android.widget.RelativeLayout rlReply;
    public final android.widget.TextView tvAbsoluteTime;
    public final com.codepath.apps.simpletweets.others.LinkifiedTextView tvBody;
    public final android.widget.TextView tvFavoriteCount;
    public final android.widget.TextView tvRetweet;
    public final android.widget.TextView tvRetweetCount;
    public final android.widget.TextView tvScreenName;
    public final android.widget.TextView tvUsername;
    // variables
    private com.codepath.apps.simpletweets.models.Tweet mTweet;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ContentDetailsBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.etReply = (android.widget.EditText) bindings[1];
        this.etReply.setTag(null);
        this.ivProfile = (android.widget.ImageView) bindings[7];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlReply = (android.widget.RelativeLayout) bindings[6];
        this.tvAbsoluteTime = (android.widget.TextView) bindings[9];
        this.tvBody = (com.codepath.apps.simpletweets.others.LinkifiedTextView) bindings[8];
        this.tvFavoriteCount = (android.widget.TextView) bindings[5];
        this.tvFavoriteCount.setTag(null);
        this.tvRetweet = (android.widget.TextView) bindings[10];
        this.tvRetweetCount = (android.widget.TextView) bindings[4];
        this.tvRetweetCount.setTag(null);
        this.tvScreenName = (android.widget.TextView) bindings[3];
        this.tvScreenName.setTag(null);
        this.tvUsername = (android.widget.TextView) bindings[2];
        this.tvUsername.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.tweet :
                setTweet((com.codepath.apps.simpletweets.models.Tweet) variable);
                return true;
        }
        return false;
    }

    public void setTweet(com.codepath.apps.simpletweets.models.Tweet tweet) {
        this.mTweet = tweet;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.tweet);
        super.requestRebind();
    }
    public com.codepath.apps.simpletweets.models.Tweet getTweet() {
        return mTweet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String stringReplyToTweetGe = null;
        int tweetGetFavoriteCoun = 0;
        com.codepath.apps.simpletweets.models.User tweetGetUserTweet = null;
        com.codepath.apps.simpletweets.models.Tweet tweet = mTweet;
        java.lang.String stringTweetGetFavori = null;
        java.lang.String tweetGetUserTweetGet = null;
        java.lang.String tweetGetUserTweetGet1 = null;
        int tweetGetRetweetCount = 0;
        java.lang.String stringTweetGetRetwee = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (tweet != null) {
                    // read tweet.getFavoriteCount()
                    tweetGetFavoriteCoun = tweet.getFavoriteCount();
                    // read tweet.getUser()
                    tweetGetUserTweet = tweet.getUser();
                    // read tweet.getRetweetCount()
                    tweetGetRetweetCount = tweet.getRetweetCount();
                }


                // read ("") + (tweet.getFavoriteCount())
                stringTweetGetFavori = ("") + (tweetGetFavoriteCoun);
                // read ("") + (tweet.getRetweetCount())
                stringTweetGetRetwee = ("") + (tweetGetRetweetCount);
                if (tweetGetUserTweet != null) {
                    // read tweet.getUser().getScreenName()
                    tweetGetUserTweetGet = tweetGetUserTweet.getScreenName();
                    // read tweet.getUser().getName()
                    tweetGetUserTweetGet1 = tweetGetUserTweet.getName();
                }


                // read ("Reply to ") + (tweet.getUser().getName())
                stringReplyToTweetGe = ("Reply to ") + (tweetGetUserTweetGet1);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.etReply.setHint(stringReplyToTweetGe);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvFavoriteCount, stringTweetGetFavori);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvRetweetCount, stringTweetGetRetwee);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvScreenName, tweetGetUserTweetGet);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvUsername, tweetGetUserTweetGet1);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ContentDetailsBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ContentDetailsBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ContentDetailsBinding>inflate(inflater, com.codepath.apps.simpletweets.R.layout.content_details, root, attachToRoot, bindingComponent);
    }
    public static ContentDetailsBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ContentDetailsBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.codepath.apps.simpletweets.R.layout.content_details, null, false), bindingComponent);
    }
    public static ContentDetailsBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ContentDetailsBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/content_details_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ContentDetailsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): tweet
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}