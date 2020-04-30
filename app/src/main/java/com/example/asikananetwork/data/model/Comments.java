package com.example.asikananetwork.data.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Comments implements Parcelable {
    private String commentText;
    private long commentTimestamp;
    private String commentID;
    private Users author;

    public Comments() {
    }

    public Comments(String commentText, Users author){
        this.commentText = commentText;
        this.commentTimestamp = System.currentTimeMillis();
        this.commentID = UUID.randomUUID().toString();
        this.author = author;

    }

    protected Comments(Parcel in) {
        commentText = in.readString();
        commentTimestamp = in.readLong();
        commentID = in.readString();
        author = in.readParcelable(Users.class.getClassLoader());
    }

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(commentText);
        parcel.writeLong(commentTimestamp);
        parcel.writeString(commentID);
        parcel.writeParcelable(author, i);
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public long getCommentTimestamp() {
        return commentTimestamp;
    }

    public void setCommentTimestamp(long commentTimestamp) {
        this.commentTimestamp = commentTimestamp;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }
}
