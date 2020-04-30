package com.example.asikananetwork.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asikananetwork.R;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        final Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterPage.this, "Step 1", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterPage.this, WelcomePage.class));
            }
        });
    }
}
