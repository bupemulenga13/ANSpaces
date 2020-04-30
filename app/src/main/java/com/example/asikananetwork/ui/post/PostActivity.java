package com.example.asikananetwork.ui.post;

import android.os.Bundle;

import com.example.asikananetwork.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showHomeFeedFragment();



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPostFragment newPostFragment = new NewPostFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, newPostFragment, getString(R.string.tag_fragment_newPost));
                transaction.addToBackStack(getString(R.string.tag_fragment_newPost));
                transaction.commit();

                Toast.makeText(PostActivity.this, "Add new post", Toast.LENGTH_LONG).show();
            }
        });



    }

    private void showHomeFeedFragment(){
        PostListFragment postListFragment = new PostListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, postListFragment, getString(R.string.tag_fragment_home));
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        transaction.commit();
    }

}
