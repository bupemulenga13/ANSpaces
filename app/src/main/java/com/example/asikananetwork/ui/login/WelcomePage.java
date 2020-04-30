package com.example.asikananetwork.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asikananetwork.R;
import com.example.asikananetwork.ui.post.PostActivity;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        final Button getStartedButton = findViewById(R.id.get_started);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomePage.this, "Home", Toast.LENGTH_LONG).show();
                startActivity(new Intent(WelcomePage.this, PostActivity.class));
            }
        });

    }
}
