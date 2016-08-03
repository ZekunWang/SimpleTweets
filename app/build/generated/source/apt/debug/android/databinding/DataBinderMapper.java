
package android.databinding;
import com.codepath.apps.simpletweets.BR;
class DataBinderMapper {
    final static int TARGET_MIN_SDK = 21;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.codepath.apps.simpletweets.R.layout.activity_details:
                    return com.codepath.apps.simpletweets.databinding.ActivityDetailsBinding.bind(view, bindingComponent);
                case com.codepath.apps.simpletweets.R.layout.content_details:
                    return com.codepath.apps.simpletweets.databinding.ContentDetailsBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case -600875192: {
                if(tag.equals("layout/activity_details_0")) {
                    return com.codepath.apps.simpletweets.R.layout.activity_details;
                }
                break;
            }
            case -1544296056: {
                if(tag.equals("layout/content_details_0")) {
                    return com.codepath.apps.simpletweets.R.layout.content_details;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"tweet"};
    }
}