package com.example.x5.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.x5.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.functions;

public class UsernameDialogFragment extends DialogFragment {
    String TAG = "Username Fragment";
    String login;

    public UsernameDialogFragment(String login) {
       this.login = login;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.username_fragment, container, false);
    }

    public void onViewCreated(View view,  Bundle savedInstanceState) {
        EditText edit_username = view.findViewById(R.id.edit_username);
        Button change = view.findViewById(R.id.change_button);
        Button cancel = view.findViewById(R.id.cancel_button);

        functions f = new functions();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edit_username.getText().toString();

                if (f.username_is_empty(username)) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Ник не может быть пустым!", Toast.LENGTH_SHORT).show();
                } else if (f.usernameIsNotFilled(username)) {
                   Toast.makeText(getActivity().getApplicationContext(),
                           "Вы не заполнили поле!", Toast.LENGTH_SHORT).show();
                } else {
                    DataBaseHelper db = new DataBaseHelper();
                    db.setUser_login(login);
                    db.update_username(username);
                    dismiss(); // закрытие фрагмента
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // закрытие фрагмента
            }
        });
    }
}
