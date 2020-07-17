package org.techtown.doitmission_08;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    String ID;
    String PW;

    public SimpleData(String id, String pw){
        ID = id;
        PW = pw;
    }

    public SimpleData(Parcel src){
        ID = src.readString();
        PW = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public SimpleData createFromParcel(Parcel in){
            return new SimpleData(in);
        }
        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ID);
        parcel.writeString(PW);
    }
}
