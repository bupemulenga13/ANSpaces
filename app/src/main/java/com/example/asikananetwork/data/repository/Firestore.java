package com.example.asikananetwork.data.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.asikananetwork.R;
import com.example.asikananetwork.data.model.Comments;
import com.example.asikananetwork.data.model.Events;
import com.example.asikananetwork.data.model.Groups;
import com.example.asikananetwork.data.model.Likes;
import com.example.asikananetwork.data.model.Posts;
import com.example.asikananetwork.data.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Firestore {

    private static final String USERS = "USERS";
    private static final String GROUPS = "SPACES";
    private static final String GROUP_POSTS = "GROUP_POSTS";
    private static final String USERS_GROUPS = "USERS_GROUPS";
    private static final String GROUPS_USERS = "GROUPS_USERS";
    private static final String POSTS_COMMENTS = "POSTS_COMMENTS";
    private static final String POSTS_LIKES = "POSTS_LIKES";
    private static final String EVENTS = "EVENTS";


    //    Collection and Document References
    public static CollectionReference getUsersReference(FirebaseFirestore fireStore) {
        return fireStore.collection(USERS);
    }

    public static DocumentReference getUserReference(FirebaseFirestore firestore, Users user) {
        return firestore.collection(USERS).document(user.getUserID());
    }

    public static CollectionReference getGroupsReference(FirebaseFirestore firestore) {
        return firestore.collection(GROUPS);
    }

    public static DocumentReference getGroupReference(FirebaseFirestore firestore, String groupId) {
        return firestore.collection(GROUPS).document(groupId);
    }

    public static CollectionReference getGroupPostsReference(FirebaseFirestore firestore, String groupId) {
        return firestore.collection(GROUPS).document(groupId).collection(GROUP_POSTS);
    }

    public static DocumentReference getPostReference(FirebaseFirestore firestore, Groups group, String postId) {
        return firestore.collection(GROUPS).document(group.getGroupId()).collection(GROUP_POSTS).document(postId);
    }

    //    Set User
    public static void setUser(FirebaseFirestore firestore, Users user) {
        firestore.collection(USERS).document(user.getUserID()).set(user);
    }

    public static void createGroup(final Context context, final FirebaseFirestore firestore, final Users user, final Groups group) {
        group.setLatestPost(new Posts(user, null, context.getString(R.string.user_created_group, user.getProfileName())));
        firestore.collection(GROUPS).document(group.getGroupId()).set(group).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                firestore.collection(GROUPS).document(group.getGroupId()).collection(GROUPS_USERS).document(user.getUserID()).set(user);
                firestore.collection(USERS).document(user.getUserID()).collection(USERS_GROUPS).document(group.getGroupId()).set(group);
            }
        });
    }

    public static void updateGroup(FirebaseFirestore firestore, Groups group) {
        firestore.collection(GROUPS).document(group.getGroupId()).set(group);
    }

    public static void joinGroup(final FirebaseFirestore firestore, final Users user, final Groups group) {
        firestore.collection(GROUPS)
                .document(group.getGroupId())
                .collection(GROUPS_USERS)
                .document(user.getUserID())
                .set(user);
        firestore.collection(USERS)
                .document(user.getUserID())
                .collection(USERS_GROUPS)
                .document(group.getGroupId())
                .set(group);
    }

    public static CollectionReference getPostsComments(FirebaseFirestore firestore) {
        return firestore.collection(POSTS_COMMENTS);
    }

    public static DocumentReference getPostCommentReference(FirebaseFirestore firestore, Comments comment) {
        return firestore.collection(POSTS_COMMENTS).document(comment.getCommentID());


    }

    public static CollectionReference getPostLikes (FirebaseFirestore firestore){
        return firestore.collection(POSTS_LIKES);
    }

    public static DocumentReference getPostLikesReference(FirebaseFirestore firestore, Likes likes){
        return  firestore.collection(POSTS_LIKES).document(likes.getId());
    }

    public static CollectionReference getEvent(FirebaseFirestore firestore){
        return firestore.collection(EVENTS);
    }

    public static DocumentReference getEventReference(FirebaseFirestore firestore, Events events){
        return firestore.collection(EVENTS).document(events.getEventID());
    }

//    Post to group
public static void postToGroup(FirebaseFirestore firestore, String groupId, Posts post) {
    firestore.collection(GROUPS)
            .document(groupId)
            .collection(GROUP_POSTS)
            .document(post.getPostID())
            .set(post);
}

    public static void addCommentToPost(FirebaseFirestore firestore, Groups group, Posts post, Comments comment) {
        firestore.collection(GROUPS)
                .document(group.getGroupId())
                .collection(GROUP_POSTS)
                .document(post.getPostID())
                .collection(POSTS_COMMENTS)
                .document(comment.getCommentID())
                .set(comment);

        // Also update the comment count on the post
        firestore.collection(GROUPS)
                .document(group.getGroupId())
                .collection(GROUP_POSTS)
                .document(post.getPostID())
                .update("numberOfComments", (post.getNumberOfComments() + 1));
    }

}