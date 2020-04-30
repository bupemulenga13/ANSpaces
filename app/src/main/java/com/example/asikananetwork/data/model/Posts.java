package com.example.asikananetwork.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.Timestamp;

public class Posts implements Parcelable {
    private String postID;
    private Users postAuthor;
    private String postTitle;
    private String postBody;
    private Timestamp timestamp;
    private long commentsCount;
    private long likesCount;
    private String postImageURL;

    public Posts(Users postAuthor, String postTitle, String postBody, String postImageURL) {
        this.postAuthor = postAuthor;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.commentsCount  = 0;
        this.likesCount = 0;
        this.postImageURL = postImageURL;
//        this.timestamp =  ;

    }

    public Posts() {
    }

    public Posts(Users user, Object o, String string) {

    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public Users getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Users postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public long getNumberOfComments() {
        return commentsCount;
    }

    public void setNumberOfComments(long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getNumberOfLikes() {
        return likesCount;
    }

    public void setNumberOfLikes(long numberOfLikes) {
        this.likesCount = numberOfLikes;
    }

    public String getPostImageURL() {
        return postImageURL;
    }

    public void setPostImageURL(String postImageURL) {
        this.postImageURL = postImageURL;
    }



    protected Posts(Parcel in) {
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
