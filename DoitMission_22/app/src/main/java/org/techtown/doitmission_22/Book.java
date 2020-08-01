package org.techtown.doitmission_22;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    String name;
    String writer;
    String contents;

    public Book(String name, String writer, String contents){
        this.name = name;
        this.writer = writer;
        this.contents = contents;
    }

    public Book(Parcel parcel) {
        name  = parcel.readString();
        writer = parcel.readString();
        contents = parcel.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(writer);
        parcel.writeString(contents);
    }
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };
}
