package com.example.x5.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.example.x5.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.activities.MainActivity;

public class DeleteFragment extends DialogFragment {
    public String TAG = "delete fragment";
    public String login;

    public DeleteFragment(String login) {
        this.login = login;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_delete, container, false);
    }

    public void onViewCreated(View view,  Bundle savedInstanceState) {
        Button yes = view.findViewById(R.id.yes_button);
        Button no = view.findViewById(R.id.no_button);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper db = new DataBaseHelper();
                db.setUser_login(login);

                db.delete_account();
                dismiss();

                Intent Intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(Intent);
            }
        });
    }
}
