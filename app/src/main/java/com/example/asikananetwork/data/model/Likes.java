package com.example.asikananetwork.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Likes implements Parcelable {

    public Likes(String id, String authorID, String postID, long createdDate) {
        this.id = id;
        this.authorID = authorID;
        this.postID = postID;
        this.createdDate = Calendar.getInstance().getTimeInMillis();
        ;
    }

    public Likes() {
    }

    private String id;
    private String authorID;
    private String postID;
    private long createdDate;


    protected Likes(Parcel in) {
        id = in.readString();
        authorID = in.readString();
        postID = in.readString();
        createdDate = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(authorID);
        dest.writeString(postID);
        dest.writeLong(createdDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Likes> CREATOR = new Creator<Likes>() {
        @Override
        public Likes createFromParcel(Parcel in) {
            return new Likes(in);
        }

        @Override
        public Likes[] newArray(int size) {
            return new Likes[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }
}
