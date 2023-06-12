package com.example.x5.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.x5.DataBaseClasses.DataBaseHelper;
import com.example.x5.R;

public class VkidFragment extends DialogFragment {
    DataBaseHelper db = new DataBaseHelper();
    String TAG = "VKID Fragment";
    String login;
    String vk_id;

    public VkidFragment(String login) {
        this.login = login;
        db.setUser_login(login);
        this.vk_id = db.get_VKid();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_vkid, container, false);
    }

    public void onViewCreated(View view,  Bundle savedInstanceState) {
        Button accept_btn = view.findViewById(R.id.accept_btn);
        Button decline_btn = view.findViewById(R.id.decline_btn);
        TextView textView = view.findViewById(R.id.Vk_id_tv);
        EditText editText = view.findViewById(R.id.vkid_et);

        textView.setText("Сейчас ваш id: " + vk_id);

        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Vk_id = editText.getText().toString();
                db.update_VKid(Vk_id);
                dismiss();
            }
        });

        decline_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
