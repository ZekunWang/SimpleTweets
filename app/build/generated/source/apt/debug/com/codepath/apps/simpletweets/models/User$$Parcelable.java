
package com.codepath.apps.simpletweets.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-07T19:47-0500")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class User$$Parcelable
    implements Parcelable, ParcelWrapper<com.codepath.apps.simpletweets.models.User>
{

    private com.codepath.apps.simpletweets.models.User user$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static User$$Parcelable.Creator$$2 CREATOR = new User$$Parcelable.Creator$$2();

    public User$$Parcelable(com.codepath.apps.simpletweets.models.User user$$2) {
        user$$0 = user$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(user$$0, parcel$$0, flags, new HashSet<Integer>());
    }

    public static void write(com.codepath.apps.simpletweets.models.User user$$1, android.os.Parcel parcel$$1, int flags$$0, Set<Integer> identitySet$$0) {
        int identity$$0 = System.identityHashCode(user$$1);
        parcel$$1 .writeInt(identity$$0);
        if (!identitySet$$0 .contains(identity$$0)) {
            identitySet$$0 .add(identity$$0);
            if (user$$1 == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                parcel$$1 .writeLong(user$$1 .uid);
                parcel$$1 .writeInt((user$$1 .following? 1 : 0));
                parcel$$1 .writeString(user$$1 .name);
                parcel$$1 .writeString(user$$1 .screenName);
                parcel$$1 .writeString(user$$1 .profileImageUrl);
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.codepath.apps.simpletweets.models.User getParcel() {
        return user$$0;
    }

    public static com.codepath.apps.simpletweets.models.User read(android.os.Parcel parcel$$3, Map<Integer, Object> identityMap$$0) {
        com.codepath.apps.simpletweets.models.User user$$3;
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$0 .containsKey(identity$$1)) {
            com.codepath.apps.simpletweets.models.User user$$4 = ((com.codepath.apps.simpletweets.models.User) identityMap$$0 .get(identity$$1));
            if ((user$$4 == null)&&(identity$$1 != 0)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return user$$4;
        }
        if (parcel$$3 .readInt() == -1) {
            user$$3 = null;
            identityMap$$0 .put(identity$$1, null);
        } else {
            com.codepath.apps.simpletweets.models.User user$$5;
            identityMap$$0 .put(identity$$1, null);
            user$$5 = new com.codepath.apps.simpletweets.models.User();
            identityMap$$0 .put(identity$$1, user$$5);
            user$$5 .uid = parcel$$3 .readLong();
            user$$5 .following = (parcel$$3 .readInt() == 1);
            user$$5 .name = parcel$$3 .readString();
            user$$5 .screenName = parcel$$3 .readString();
            user$$5 .profileImageUrl = parcel$$3 .readString();
            user$$3 = user$$5;
        }
        return user$$3;
    }

    public final static class Creator$$2
        implements Creator<User$$Parcelable>
    {


        @Override
        public User$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new User$$Parcelable(read(parcel$$2, new HashMap<Integer, Object>()));
        }

        @Override
        public User$$Parcelable[] newArray(int size) {
            return new User$$Parcelable[size] ;
        }

    }

}
