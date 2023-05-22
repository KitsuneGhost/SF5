package com.example.x5.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x5.R;
import com.example.x5.fragments.CartFragment;
import com.example.x5.fragments.ProfileFragment;
import com.example.x5.fragments.QuestsFragment;

public class AppActivity extends AppCompatActivity {

    protected void setDefaultImages() {
        ImageView quests_iv = findViewById(R.id.quests_iv);
        TextView quests_tv = findViewById(R.id.quests_tv);
        quests_iv.setImageResource(R.drawable.bookmark);
        quests_tv.setTextColor(getResources().getColor(R.color.ui_gray));

        ImageView cart_iv = findViewById(R.id.cart_iv);
        TextView cart_tv = findViewById(R.id.cart_tv);
        cart_iv.setImageResource(R.drawable.shopping_cart);
        cart_tv.setTextColor(getResources().getColor(R.color.ui_gray));

        ImageView profile_iv = findViewById(R.id.profile_iv);
        TextView profile_tv = findViewById(R.id.profile_tv);
        profile_iv.setImageResource(R.drawable.user);
        profile_tv.setTextColor(getResources().getColor(R.color.ui_gray));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        CartFragment cartFragment = new CartFragment();
        ProfileFragment profileFragment = new ProfileFragment();
        QuestsFragment questsFragment = new QuestsFragment();
        ft.add(R.id.fragmentLayout, cartFragment);
        ft.commit();

        Bundle arguments = getIntent().getExtras(); // получаем интент из предыдущей активности
        String login = arguments.get("login_key").toString(); // получаем логин из интента
        Bundle bundle = new Bundle();
        bundle.putString("login_key", login); // создаем передачу логина
        profileFragment.setArguments(bundle); // передаем логин в фрагмент

        LinearLayout profile_layout = findViewById(R.id.profile_layout);
        LinearLayout quests_layout = findViewById(R.id.quests_layout);
        LinearLayout cart_layout = findViewById(R.id.cart_layout);

        quests_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultImages();
                ImageView imageView = findViewById(R.id.quests_iv);
                TextView textView = findViewById(R.id.quests_tv);
                imageView.setImageResource(R.drawable.bookmark_red);
                textView.setTextColor(getResources().getColor(R.color.ui_red));
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentLayout, questsFragment);
                ft.commit();
            }
        });

        profile_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultImages();
                ImageView imageView = findViewById(R.id.profile_iv);
                TextView textView = findViewById(R.id.profile_tv);
                imageView.setImageResource(R.drawable.user_red);
                textView.setTextColor(getResources().getColor(R.color.ui_red));
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentLayout, profileFragment);
                ft.commit();
            }
        });

        cart_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultImages();
                ImageView imageView = findViewById(R.id.cart_iv);
                TextView textView = findViewById(R.id.cart_tv);
                imageView.setImageResource(R.drawable.shopping_cart_red);
                textView.setTextColor(getResources().getColor(R.color.ui_red));
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentLayout, cartFragment);
                ft.commit();
            }
        });
    }
}