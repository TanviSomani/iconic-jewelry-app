package com.example.iconicjewelry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TermsOfUseActivity extends AppCompatActivity {

    ImageView back;
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_use);

        back = findViewById(R.id.btnBack);
        toolbar_title = findViewById(R.id.general_text);

        toolbar_title.setText("Terms of Use");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });

    }
}