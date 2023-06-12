package com.example.x5.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x5.DataBaseClasses.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.fragments.CartFragment;
import com.example.x5.fragments.ProfileFragment;
import com.example.x5.fragments.QuestsFragment;

import java.util.ArrayList;

public class AppActivity extends AppCompatActivity {
    DataBaseHelper db = new DataBaseHelper();

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

        Bundle arguments = getIntent().getExtras(); // получаем интент из предыдущей активности
        String login = arguments.get("login_key").toString(); // получаем логин из интента

        db.connect();
        db.setUser_login(login);

        ArrayList<String> profile_data = db.profile_fragment_setup();
        ArrayList<String[]> products = db.getProductList();

        FragmentManager fm = getSupportFragmentManager(); // нужен для смены фрагментов
        FragmentTransaction ft = fm.beginTransaction();

        CartFragment cartFragment = new CartFragment(products); // экземпляры фрагментоф
        ProfileFragment profileFragment = new ProfileFragment(profile_data, login);

        QuestsFragment questsFragment = new QuestsFragment();
        ft.add(R.id.fragmentLayout, cartFragment); // сразу показываем фрагмент корзины
        ft.commit();

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