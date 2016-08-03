    package com.codepath.apps.simpletweets.adapters;

    import com.bumptech.glide.Glide;
    import com.bumptech.glide.load.engine.DiskCacheStrategy;
    import com.codepath.apps.simpletweets.R;
    import com.codepath.apps.simpletweets.models.Tweet;
    import com.codepath.apps.simpletweets.models.ViewHolder;
    import com.codepath.apps.simpletweets.others.ParseRelativeDate;
    import com.codepath.apps.simpletweets.others.PatternEditableBuilder;

    import android.content.Context;
    import android.graphics.Color;
    import android.support.v4.content.ContextCompat;
    import android.support.v7.widget.RecyclerView;
    import android.util.DisplayMetrics;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Toast;

    import java.util.List;
    import java.util.regex.Pattern;

    import jp.wasabeef.glide.transformations.BlurTransformation;
    import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

    public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        // Store a member variable for the contacts
        private List<Tweet> contacts;
        // Store the context for easy access
        private Context context;
        // Define listener member variable
        private static OnItemClickListener listener;

        double width;
        int height;

        // Define the listener interface
        public interface OnItemClickListener {
            void onItemClick(View itemView, int position);
        }

        public static OnItemClickListener getListener() {
            return listener;
        }

        // Define the method that allows the parent activity or fragment to define the listener
        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        // Pass in the contact array into the constructor
        public ContactsAdapter(Context context, List<Tweet> contacts) {
            this.contacts = contacts;
            this.context = context;
        }

        // Easy access to the context object in the recyclerview
        private Context getContext() {
            return context;
        }

        // Usually involves inflating a layout from XML and returning the holder
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            // distinguish view by viewType
            View contactView = inflater.inflate(R.layout.item_tweet, parent, false);
            viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            // get data item for position
            Tweet tweet = contacts.get(position);
            ViewHolder vh = (ViewHolder) viewHolder;
            // Populate data
            vh.getTvUsername().setText(tweet.getUser().getName());
            vh.getTvScreenName().setText("@" + tweet.getUser().getScreenName());
            vh.getTvRelativeTime().setText(ParseRelativeDate.getRelativeTimeAgo(tweet.getCreatedAt()));
            vh.getTvBody().setText(tweet.getBody());
            vh.getTvRetweetCount().setText("" + tweet.getRetweetCount());
            vh.getTvFavoriteCount().setText("" + tweet.getFavoriteCount());
            vh.getIvFavorite().setImageResource(tweet.isFavorited() ? R.drawable.ic_heart_lighted : R.drawable.ic_heart);

            // Search for @ and #
            int color = ContextCompat.getColor(context,R.color.colorPrimary);
            new PatternEditableBuilder().
                addPattern(Pattern.compile("\\@(\\w+)"), color,
                    new PatternEditableBuilder.SpannableClickedListener() {
                        @Override
                        public void onSpanClicked(String text) {
                            Toast.makeText(getContext(), "Clicked username: " + text,
                                Toast.LENGTH_SHORT).show();
                        }
                    }).
                addPattern(Pattern.compile("\\#(\\w+)"), color,
                    new PatternEditableBuilder.SpannableClickedListener() {
                        @Override
                        public void onSpanClicked(String text) {
                            Toast.makeText(getContext(), "Clicked hashtag: " + text,
                                Toast.LENGTH_SHORT).show();
                        }
                    }).into(vh.getTvBody());

            Glide.with(getContext())
                .load(tweet.getUser().getProfileImageUrl())
                .placeholder(R.drawable.ic_launcher)
                .bitmapTransform(new RoundedCornersTransformation(getContext(), 5, 0))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(vh.getIvProfile());
        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return contacts.size();
        }

        public void clear() {
            contacts.clear();
            notifyDataSetChanged();
        }

        public void addAll(List<Tweet> list) {
            int pos = contacts.size();
            if (list != null) {
                contacts.addAll(list);
                notifyDataSetChanged();
            }
        }

        public void add(Tweet newTweet) {
            if (newTweet != null) {
                contacts.add(newTweet);
                notifyDataSetChanged();
            }
        }

        public void add(int pos, Tweet newTweet) {
            if (newTweet != null) {
                contacts.add(pos, newTweet);
                notifyDataSetChanged();
            }
        }
    }
