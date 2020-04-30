package com.example.asikananetwork.ui.group.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.asikananetwork.data.model.Groups;
import com.example.asikananetwork.data.repository.Firestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GroupViewModel extends ViewModel {
    private static final String TAG = "GroupViewModel";

    private MutableLiveData<String> mText;
    private Groups mGroups;
    private MutableLiveData<ArrayList<Groups>> groupListModelData = new MutableLiveData<>();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private ArrayList<Groups> groupsArrayList = new ArrayList<>();

    public MutableLiveData<ArrayList<Groups>> getGroups(){
        this.mGroups = new Groups();
        if (groupListModelData != null){
            loadGroups();
        }
        return groupListModelData;
    }

    private void loadGroups() {
        Log.d(TAG, "loadGroups: method called");
        Firestore.getGroupsReference(firestore)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.exists()){
                                    groupsArrayList.add(document.toObject(Groups.class));
                                    Log.d(TAG, document.getId() + " => " + document.getData());

                                }
                                groupListModelData.setValue(groupsArrayList);
                            }
                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

//    public GroupViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }
}