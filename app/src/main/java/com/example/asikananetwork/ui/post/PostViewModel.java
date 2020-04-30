package com.example.asikananetwork.ui.post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.asikananetwork.data.model.Posts;
import com.example.asikananetwork.data.repository.PostsRepo;

import java.util.List;

public class PostViewModel extends ViewModel implements PostsRepo.OnFireStoreTaskComplete {
    private static final String TAG = "PostViewModel";
    private Posts posts;


    private MutableLiveData<List<Posts>> postsListModelData = new MutableLiveData<>();

    LiveData<List<Posts>> getPostsListModelData() {
        return postsListModelData;
    }


    public PostViewModel(){
        Log.d(TAG, "PostViewModel: initialised");
        PostsRepo postsRepo = new PostsRepo(this);
        postsRepo.getPostData();
    }



    @Override
    public void postDataAdded(List<Posts>arrayList) {
        postsListModelData.setValue(arrayList);


    }

    @Override
    public void onError(Exception e) {

    }
}
