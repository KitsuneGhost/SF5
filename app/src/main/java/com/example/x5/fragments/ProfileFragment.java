package com.example.x5.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.x5.DataBaseHelper;
import com.example.x5.R;


public class ProfileFragment extends Fragment {

    String login;
    String username;
    String level;
    int xp;
    DataBaseHelper db = new DataBaseHelper();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        login = getArguments().getString("login_key");
        db.setUser_login(login);
        username = db.get_username();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated (View view,  Bundle savedInstanceState) {
        Button change_un_btn = view.findViewById(R.id.change_username_btn);
        TextView level_tv = view.findViewById(R.id.level_tv);
        ProgressBar level_bar = view.findViewById(R.id.progressBar);

        level = level_tv.getText().toString() + " " + db.get_level();
        level_bar.setMax(100);
        level_bar.setProgress(db.get_xp());
        ChangeUsername(username);

        level_tv.setText(level);

        change_un_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsernameDialogFragment fragment = new UsernameDialogFragment(login);
                fragment.show(getActivity().getSupportFragmentManager(), fragment.TAG);
            }
        });
    }

    public void ChangeUsername (String username) {
        TextView avatar_letter = getView().findViewById(R.id.firstLetter_tv);
        TextView username_tv = getView().findViewById(R.id.UserName_tv);
        String first_letter = "" + username.charAt(0);

        username_tv.setText(username);
        avatar_letter.setText(first_letter);
    }
}