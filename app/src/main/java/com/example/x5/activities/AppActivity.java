package com.example.x5.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.x5.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.fragments.CartFragment;

public class AppActivity extends AppCompatActivity {

    protected void setDefaultImages() {
        ImageButton quests = findViewById(R.id.quests_btn);
        quests.setImageResource(R.drawable.bookmark);

        ImageButton cart = findViewById(R.id.shopping_btn);
        cart.setImageResource(R.drawable.shopping_cart);

        ImageButton profile = findViewById(R.id.profile_btn);
        profile.setImageResource(R.drawable.user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        DataBaseHelper db = new DataBaseHelper();

        CartFragment cartFragment = new CartFragment();

        ft.add(R.id.fragmentLayout, cartFragment);
        ft.commit();

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

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultImages();
                profile_btn.setImageResource(R.drawable.user_red);
            }
        });

        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultImages();
                cart_button.setImageResource(R.drawable.shopping_cart_red);
            }
        });
    }
}