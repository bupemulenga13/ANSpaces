package com.example.asikananetwork.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.asikananetwork.data.model.Posts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class PostsRepo {
    private static final String TAG = "PostsRepo";
    private OnFireStoreTaskComplete onFireStoreTaskComplete;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firestore.collection("POSTS");
    private Posts posts;

    public PostsRepo(){}


    public PostsRepo(OnFireStoreTaskComplete onFireStoreTaskComplete){
        this.onFireStoreTaskComplete = onFireStoreTaskComplete;

    }

//    Method to retrieve data from database
    public  void getPostData(){
        Log.d(TAG, "getPostData: called");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    onFireStoreTaskComplete.postDataAdded(Objects.requireNonNull(task.getResult()).toObjects(Posts.class));
                }else {
                    onFireStoreTaskComplete.onError(task.getException());

                }
            }
        });
    }

//    Create interface to send data from repository to our ViewModel
    public interface OnFireStoreTaskComplete{
        void postDataAdded(List<Posts> arrayList);
        void onError(Exception e);
    }



}
