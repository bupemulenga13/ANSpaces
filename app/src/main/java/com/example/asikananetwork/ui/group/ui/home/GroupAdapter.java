package com.example.asikananetwork.ui.group.ui.home;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asikananetwork.R;
import com.example.asikananetwork.data.model.Groups;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    private static final String TAG = "GroupAdapter";
    private ArrayList<Groups> groupList;
    private Context mContext;

    public GroupAdapter(Context context,ArrayList<Groups> groupList) {
        this.groupList = groupList;
        this.mContext = context;
    }

    public void setGroups(ArrayList<Groups> groups){
        groupList = groups;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        mContext = parent.getContext();
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: group Adapter started bind");
        Groups groups = groupList.get(position);
        holder.bind(mContext, groups);
    }


    @Override
    public int getItemCount() {
        return groupList == null ? 0 : groupList.size();
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView groupTitle, groupDesc;
        ImageView groupImage;



        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            groupTitle = itemView.findViewById(R.id.group_title);
            groupDesc = itemView.findViewById(R.id.group_description);
            groupImage = itemView.findViewById(R.id.group_image);


            itemView.setOnClickListener(this);

        }
        public void bind(Context context, Groups group) {
            if (group.getGroupImageUrl() == null){
                groupImage.setVisibility(View.GONE);
            } else{
                groupImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(group.getGroupImageUrl()).centerCrop().into(groupImage);

            }
            groupTitle.setText(group.getGroupName());
            groupDesc.setText(group.getGroupDescription());
        }
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d("Click", String.valueOf(position));



        }
    }

}
