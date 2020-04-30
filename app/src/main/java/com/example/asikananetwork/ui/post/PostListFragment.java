package com.example.asikananetwork.ui.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asikananetwork.R;
import com.example.asikananetwork.data.model.Posts;

import java.util.List;

public class PostListFragment extends Fragment {
    private static final String TAG = "PostListFragment";
    private RecyclerView recyclerView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public PostListFragment(){
        Log.d(TAG, "PostListFragment: called");
    }


    public static PostListFragment newInstance(String param1, String param2) {
        PostListFragment fragment = new PostListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Create a new instance of this fragment
     * @return A new instance of fragment FirstFragment.
     */
    public static PostListFragment newInstance() {
        return new PostListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home_feed, container, false);

        recyclerView = view.findViewById(R.id.home_feed_recycler_view);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
//        recyclerView.setAdapter("postAdapter");

        return view;
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: from HomeFeed called");
        super.onActivityCreated(savedInstanceState);

        PostViewModel postViewModel = new ViewModelProvider(getActivity()).get(PostViewModel.class);
        postViewModel.getPostsListModelData().observe(getViewLifecycleOwner(), new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> postListModels) {
                Log.d(TAG, "onChanged: called");


            }
        });
    }


}
