
package com.codepath.apps.simpletweets.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-07T00:06-0500")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Medium$$Parcelable
    implements Parcelable, ParcelWrapper<com.codepath.apps.simpletweets.models.Medium>
{

    private com.codepath.apps.simpletweets.models.Medium medium$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Medium$$Parcelable.Creator$$0 CREATOR = new Medium$$Parcelable.Creator$$0();

    public Medium$$Parcelable(com.codepath.apps.simpletweets.models.Medium medium$$2) {
        medium$$0 = medium$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(medium$$0, parcel$$0, flags, new HashSet<java.lang.Integer>());
    }

    public static void write(com.codepath.apps.simpletweets.models.Medium medium$$1, android.os.Parcel parcel$$1, int flags$$0, Set<java.lang.Integer> identitySet$$0) {
        int identity$$0 = System.identityHashCode(medium$$1);
        parcel$$1 .writeInt(identity$$0);
        if (!identitySet$$0 .contains(identity$$0)) {
            identitySet$$0 .add(identity$$0);
            if (medium$$1 == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                parcel$$1 .writeString(medium$$1 .mediaUrl);
                if (medium$$1 .videos == null) {
                    parcel$$1 .writeInt(-1);
                } else {
                    parcel$$1 .writeInt(medium$$1 .videos.size());
                    for (java.util.Map.Entry<java.lang.Integer, java.lang.String> entry$$0 : ((java.util.Map<java.lang.Integer, java.lang.String> ) medium$$1 .videos).entrySet()) {
                        if (entry$$0 .getKey() == null) {
                            parcel$$1 .writeInt(-1);
                        } else {
                            parcel$$1 .writeInt(1);
                            parcel$$1 .writeInt(entry$$0 .getKey());
                        }
                        parcel$$1 .writeString(entry$$0 .getValue());
                    }
                }
                com.codepath.apps.simpletweets.models.Tweet$$Parcelable.write(medium$$1 .tweet, parcel$$1, flags$$0, identitySet$$0);
                parcel$$1 .writeString(medium$$1 .type);
                parcel$$1 .writeString(medium$$1 .url);
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.codepath.apps.simpletweets.models.Medium getParcel() {
        return medium$$0;
    }

    public static com.codepath.apps.simpletweets.models.Medium read(android.os.Parcel parcel$$3, java.util.Map<java.lang.Integer, Object> identityMap$$0) {
        com.codepath.apps.simpletweets.models.Medium medium$$3;
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$0 .containsKey(identity$$1)) {
            com.codepath.apps.simpletweets.models.Medium medium$$4 = ((com.codepath.apps.simpletweets.models.Medium) identityMap$$0 .get(identity$$1));
            if ((medium$$4 == null)&&(identity$$1 != 0)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return medium$$4;
        }
        if (parcel$$3 .readInt() == -1) {
            medium$$3 = null;
            identityMap$$0 .put(identity$$1, null);
        } else {
            com.codepath.apps.simpletweets.models.Medium medium$$5;
            identityMap$$0 .put(identity$$1, null);
            medium$$5 = new com.codepath.apps.simpletweets.models.Medium();
            identityMap$$0 .put(identity$$1, medium$$5);
            medium$$5 .mediaUrl = parcel$$3 .readString();
            int int$$0 = parcel$$3 .readInt();
            HashMap<java.lang.Integer, java.lang.String> map$$0;
            if (int$$0 < 0) {
                map$$0 = null;
            } else {
                map$$0 = new HashMap<java.lang.Integer, java.lang.String>(int$$0);
                for (int int$$1 = 0; (int$$1 <int$$0); int$$1 ++) {
                    int int$$2 = parcel$$3 .readInt();
                    java.lang.Integer integer$$0;
                    if (int$$2 < 0) {
                        integer$$0 = null;
                    } else {
                        integer$$0 = parcel$$3 .readInt();
                    }
                    java.lang.Integer integer$$1 = integer$$0;
                    java.lang.String string$$0 = parcel$$3 .readString();
                    map$$0 .put(integer$$1, string$$0);
                }
            }
            medium$$5 .videos = map$$0;
            Tweet tweet$$0 = com.codepath.apps.simpletweets.models.Tweet$$Parcelable.read(parcel$$3, identityMap$$0);
            medium$$5 .tweet = tweet$$0;
            medium$$5 .type = parcel$$3 .readString();
            medium$$5 .url = parcel$$3 .readString();
            medium$$3 = medium$$5;
        }
        return medium$$3;
    }

    public final static class Creator$$0
        implements Creator<Medium$$Parcelable>
    {


        @Override
        public Medium$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Medium$$Parcelable(read(parcel$$2, new HashMap<java.lang.Integer, Object>()));
        }

        @Override
        public Medium$$Parcelable[] newArray(int size) {
            return new Medium$$Parcelable[size] ;
        }

    }

}
