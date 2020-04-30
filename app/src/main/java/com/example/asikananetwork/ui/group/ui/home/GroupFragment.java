package com.example.asikananetwork.ui.group.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asikananetwork.R;
import com.example.asikananetwork.data.model.Groups;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;


public class GroupFragment extends Fragment {
    private static final String TAG = "GroupFragment";
    private ProgressBar mProgressBar;
    private ConstraintLayout mEmptyMessageContainer;
    private GroupViewModel groupViewModel;
    private RecyclerView mRecyclerView;
    private GroupAdapter mGroupAdapter;
    private FloatingActionButton mFab;
    private Groups mGroups;
    private MutableLiveData<ArrayList<Groups>> mFirebaseGroup;
    private TextView mGroupName, mGroupDesc;
    private Button mJoin;
    private ImageView mGroupImage;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_group, container, false);


        mProgressBar = root.findViewById(R.id.group_list_pb);
        mEmptyMessageContainer = root.findViewById(R.id.empty_message_container);
        mGroups = new Groups();

        mFab = root.findViewById(R.id.fab_add_new_group);
        mRecyclerView = root.findViewById(R.id.group_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mGroupAdapter = new GroupAdapter(getContext(), new ArrayList<Groups>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGroupAdapter);

        return root;
    }

    private void updateUI(ArrayList<Groups> groups){
        if (groups == null || groups.size() == 0){
            mEmptyMessageContainer.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);

        } else {
            mEmptyMessageContainer.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mGroupAdapter.setGroups(groups);
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        final TextView textView = root.findViewById(R.id.text_home);
         mGroupName = view.findViewById(R.id.group_title);
         mGroupDesc = view.findViewById(R.id.group_description);
         mGroupImage = view.findViewById(R.id.group_image);
         mJoin = view.findViewById(R.id.follow_button);



        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewGroupFragment newGroupFragment = new NewGroupFragment();
                FragmentManager manager =getParentFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment_group, newGroupFragment, "New Group")
                        .addToBackStack("New Group")
                        .commit();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        groupViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        mFirebaseGroup = groupViewModel.getGroups();
        mFirebaseGroup.observe(getViewLifecycleOwner(), new Observer<ArrayList<Groups>>() {
            @Override
            public void onChanged(ArrayList<Groups> groups) {
                Log.d(TAG, "onChanged: " + groups);
                mGroupName.setText(mGroups.getGroupName());
                mGroupDesc.setText(mGroups.getGroupDescription());
                Glide.with(requireContext()).load(mGroups.getGroupImageUrl()).into(mGroupImage);

                Log.d(TAG, "onChanged: textView" + mGroupName);

                updateUI(groups);

            }
        });
    }

    //     groupViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
//////                textView.setText(s);
////            }
////        });
}
