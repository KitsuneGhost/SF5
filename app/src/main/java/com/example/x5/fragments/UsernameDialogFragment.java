package com.example.x5.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.x5.DataBaseClasses.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.Functions;

public class UsernameDialogFragment extends DialogFragment {
    String TAG = "Username Fragment";
    String login;
    DataBaseHelper db = new DataBaseHelper();

    public UsernameDialogFragment(String login) {
       this.login = login;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable (Color.TRANSPARENT));
        return inflater.inflate(R.layout.username_fragment, container, false);
    }

    public void onViewCreated(View view,  Bundle savedInstanceState) {
        EditText edit_username = view.findViewById(R.id.edit_username);
        Button change = view.findViewById(R.id.change_button);
        Button cancel = view.findViewById(R.id.cancel_button);

        Functions f = new Functions();

        db.connect();

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
                } else if (f.usernameIsTooLong(username)) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Ник слишком длинный! Максимум символов:8.",
                            Toast.LENGTH_SHORT).show();
                } else {
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

    public void onDestroy() {
        super.onDestroy();
        db.disconnect();
    }
}
