package com.example.x5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.x5.R;

public class AppActivity extends AppCompatActivity {

    protected void setDefaultImages() {
        ImageButton quests = findViewById(R.id.quests_btn);
        quests.setImageResource(R.drawable.bookmark);
        ImageButton cart = findViewById(R.id.shopping_btn);
        cart.setImageResource(R.drawable.shopping_cart);
        ImageButton profile = findViewById(R.id.profile_btn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        ImageButton profile_btn = findViewById(R.id.profile_btn);
        ImageButton cart_button = findViewById(R.id.shopping_btn);
        ImageButton quests_btn = findViewById(R.id.quests_btn);

        quests_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultImages();
                quests_btn.setImageResource(R.drawable.bookmark_red);
            }
        });
    }
}