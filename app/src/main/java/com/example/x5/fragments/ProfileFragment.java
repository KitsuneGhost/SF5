package com.example.x5.fragments;

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

import com.example.x5.DataBaseHelper;
import com.example.x5.R;

import java.util.Objects;


public class ProfileFragment extends Fragment {

    String login;
    String username;
    String level;
    DataBaseHelper db = new DataBaseHelper();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        login = getArguments().getString("login_key"); // получаем логин из AppActivity
        db.setUser_login(login);
        username = db.get_username();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated (View view, Bundle savedInstanceState) {
        Button change_un_btn = view.findViewById(R.id.change_username_btn);
        Button delete_acc = view.findViewById(R.id.delete_btn);
        Button change_vkid_btn = view.findViewById(R.id.vkid_change_btn);
        TextView level_tv = view.findViewById(R.id.level_tv);
        ProgressBar level_bar = view.findViewById(R.id.progressBar);
        Switch switch_theme = view.findViewById(R.id.theme_switch);

        level = level_tv.getText().toString() + " " + db.get_level(); // получаем уровень из бд
        ChangeUsername(username); // меняем ник на text view

        level_tv.setText(level); // выводин на экран уровень
        level_bar.setMax(100); // задаем максимальное количество опыта
        level_bar.setProgress(db.get_xp()); // заполняем progressbar

        change_un_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsernameDialogFragment fragment = new UsernameDialogFragment(login);
                fragment.show(getActivity().getSupportFragmentManager(), fragment.TAG); // показываем фрагмент
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
                Toast.makeText(getActivity().getApplicationContext(),
                        "Эта функция еще в разработке!", Toast.LENGTH_SHORT).show();
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