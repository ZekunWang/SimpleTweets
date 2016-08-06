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
        sViewsWithIds.put(R.id.rlReply, 9);
        sViewsWithIds.put(R.id.btnCompose, 10);
        sViewsWithIds.put(R.id.tvAvailableChars, 11);
        sViewsWithIds.put(R.id.svDetails, 12);
        sViewsWithIds.put(R.id.tvBody, 13);
        sViewsWithIds.put(R.id.ivMedia, 14);
        sViewsWithIds.put(R.id.tvRetweet, 15);
        sViewsWithIds.put(R.id.ivReply, 16);
    }
    // views
    public final android.widget.Button btnCompose;
    public final android.widget.EditText etReply;
    public final android.widget.ImageView ivFavorite;
    public final android.widget.ImageView ivMedia;
    public final android.widget.ImageView ivProfile;
    public final android.widget.ImageView ivReply;
    private final android.widget.RelativeLayout mboundView0;
    public final android.widget.RelativeLayout rlReply;
    public final android.widget.ScrollView svDetails;
    public final android.widget.TextView tvAbsoluteTime;
    public final android.widget.TextView tvAvailableChars;
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
        final Object[] bindings = mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds);
        this.btnCompose = (android.widget.Button) bindings[10];
        this.etReply = (android.widget.EditText) bindings[1];
        this.etReply.setTag(null);
        this.ivFavorite = (android.widget.ImageView) bindings[8];
        this.ivFavorite.setTag(null);
        this.ivMedia = (android.widget.ImageView) bindings[14];
        this.ivProfile = (android.widget.ImageView) bindings[2];
        this.ivProfile.setTag(null);
        this.ivReply = (android.widget.ImageView) bindings[16];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlReply = (android.widget.RelativeLayout) bindings[9];
        this.svDetails = (android.widget.ScrollView) bindings[12];
        this.tvAbsoluteTime = (android.widget.TextView) bindings[5];
        this.tvAbsoluteTime.setTag(null);
        this.tvAvailableChars = (android.widget.TextView) bindings[11];
        this.tvBody = (com.codepath.apps.simpletweets.others.LinkifiedTextView) bindings[13];
        this.tvFavoriteCount = (android.widget.TextView) bindings[7];
        this.tvFavoriteCount.setTag(null);
        this.tvRetweet = (android.widget.TextView) bindings[15];
        this.tvRetweetCount = (android.widget.TextView) bindings[6];
        this.tvRetweetCount.setTag(null);
        this.tvScreenName = (android.widget.TextView) bindings[4];
        this.tvScreenName.setTag(null);
        this.tvUsername = (android.widget.TextView) bindings[3];
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
        java.lang.String tweetGetUserTweetGet = null;
        android.graphics.drawable.Drawable tweetIsFavoritedTwee = null;
        java.lang.String stringReplyToTweetGe = null;
        int tweetGetFavoriteCoun = 0;
        com.codepath.apps.simpletweets.models.User tweetGetUserTweet = null;
        com.codepath.apps.simpletweets.models.Tweet tweet = mTweet;
        java.lang.String stringTweetGetFavori = null;
        java.lang.String tweetGetUserTweetGet1 = null;
        java.lang.String tweetGetUserTweetGet2 = null;
        boolean tweetIsFavoritedTwee1 = false;
        int tweetGetRetweetCount = 0;
        java.lang.String tweetGetCreatedAtTwe = null;
        java.lang.String stringTweetGetRetwee = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (tweet != null) {
                    // read tweet.getFavoriteCount()
                    tweetGetFavoriteCoun = tweet.getFavoriteCount();
                    // read tweet.getUser()
                    tweetGetUserTweet = tweet.getUser();
                    // read tweet.isFavorited()
                    tweetIsFavoritedTwee1 = tweet.isFavorited();
                    // read tweet.getRetweetCount()
                    tweetGetRetweetCount = tweet.getRetweetCount();
                    // read tweet.getCreatedAt()
                    tweetGetCreatedAtTwe = tweet.getCreatedAt();
                }
                if((dirtyFlags & 0x3L) != 0) {
                    if (tweetIsFavoritedTwee1) {
                        dirtyFlags |= 0x8L;
                    } else {
                        dirtyFlags |= 0x4L;
                    }}


                // read ("") + (tweet.getFavoriteCount())
                stringTweetGetFavori = ("") + (tweetGetFavoriteCoun);
                // read tweet.isFavorited() ? @android:drawable/ic_heart_lighted : @android:drawable/ic_heart
                tweetIsFavoritedTwee = (tweetIsFavoritedTwee1) ? (getDrawableFromResource(R.drawable.ic_heart_lighted)) : (getDrawableFromResource(R.drawable.ic_heart));
                // read ("") + (tweet.getRetweetCount())
                stringTweetGetRetwee = ("") + (tweetGetRetweetCount);
                if (tweetGetUserTweet != null) {
                    // read tweet.getUser().getProfileImageUrl()
                    tweetGetUserTweetGet = tweetGetUserTweet.getProfileImageUrl();
                    // read tweet.getUser().getScreenName()
                    tweetGetUserTweetGet1 = tweetGetUserTweet.getScreenName();
                    // read tweet.getUser().getName()
                    tweetGetUserTweetGet2 = tweetGetUserTweet.getName();
                }


                // read ("Reply to ") + (tweet.getUser().getName())
                stringReplyToTweetGe = ("Reply to ") + (tweetGetUserTweetGet2);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.etReply.setHint(stringReplyToTweetGe);
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.ivFavorite, tweetIsFavoritedTwee);
            com.codepath.apps.simpletweets.adapters.BindingAdapterUtils.loadImage(this.ivProfile, tweetGetUserTweetGet);
            com.codepath.apps.simpletweets.adapters.BindingAdapterUtils.loadTime(this.tvAbsoluteTime, tweetGetCreatedAtTwe);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvFavoriteCount, stringTweetGetFavori);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvRetweetCount, stringTweetGetRetwee);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvScreenName, tweetGetUserTweetGet1);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvUsername, tweetGetUserTweetGet2);
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
        flag 2 (0x3L): tweet.isFavorited() ? @android:drawable/ic_heart_lighted : @android:drawable/ic_heart
        flag 3 (0x4L): tweet.isFavorited() ? @android:drawable/ic_heart_lighted : @android:drawable/ic_heart
    flag mapping end*/
    //end
}