package com.example.x5.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.x5.DataBaseClasses.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.activities.MainActivity;

import java.util.ArrayList;
import java.util.Objects;


public class ProfileFragment extends Fragment {

    String login;
    String username;
    String level;
    ArrayList<String> data;
    int xp;
    DataBaseHelper db = new DataBaseHelper();

    public ProfileFragment (ArrayList<String> data, String login) {
        db.connect();
        db.setUser_login(login);
        this.data = data;
        this.login = login;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        username = data.get(0);
        level = data.get(1);
        xp = Integer.parseInt(data.get(2));
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated (View view, Bundle savedInstanceState) {
        Button change_un_btn = view.findViewById(R.id.change_username_btn);
        Button delete_acc = view.findViewById(R.id.delete_btn);
        Button change_vkid_btn = view.findViewById(R.id.vkid_change_btn);
        Button log_out_btn = view.findViewById(R.id.log_out_btn);
        Button update_btn = view.findViewById(R.id.update_btn);
        TextView level_tv = view.findViewById(R.id.level_tv);
        ProgressBar level_bar = view.findViewById(R.id.progressBar);
        Switch switch_theme = view.findViewById(R.id.theme_switch);

        level = "Уровень: " + level; // получаем уровень из бд
        ChangeUsername(username); // меняем ник на text view

        level_tv.setText(level); // выводин на экран уровень
        level_bar.setMax(100); // задаем максимальное количество опыта
        level_bar.setProgress(xp); // заполняем progressbar

        change_un_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsernameDialogFragment fragment = new UsernameDialogFragment(login);
                fragment.show(getActivity().getSupportFragmentManager(), fragment.TAG);// показываем фрагмент
                update_btn.setVisibility(View.VISIBLE);
            }
        });

        switch_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Смена темы еще в разработке!", Toast.LENGTH_SHORT).show();
            }
        });

        delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(login, "test")) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Это тестовый аккаунт, его нельзя удалить!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    DeleteFragment fragment = new DeleteFragment(login);
                    fragment.show(getActivity().getSupportFragmentManager(), fragment.TAG);
                }
            }
        });

        change_vkid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VkidFragment fragment = new VkidFragment(login);
                fragment.show(getActivity().getSupportFragmentManager(), fragment.TAG);
            }
        });

        log_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(Intent);
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = db.get_username();
                ChangeUsername(username);
                update_btn.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void ChangeUsername (String username) {
        TextView avatar_letter = getView().findViewById(R.id.firstLetter_tv);
        TextView username_tv = getView().findViewById(R.id.UserName_tv);
        String first_letter = "" + username.charAt(0);

        username_tv.setText(username); // меняем надписи в text view
        avatar_letter.setText(first_letter);
    }
}