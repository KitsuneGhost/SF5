package com.example.x5.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.x5.R;


public class QuestsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quests, container, false);
    }

    public void onViewCreated (View view, Bundle savedInstanceState) {
        Button info_btn = view.findViewById(R.id.info_btn);
        TextView challenge = view.findViewById(R.id.challenges);
        TextView tasks = view.findViewById(R.id.tasks_tv);

        challenge.setText("Посещать школу 5 дней подряд.\n(0/5, 4 дня)\nНаграда:50 Опыта, 10$");
        tasks.setText("Получить 5 по географии\n(0/1, 1 день, 5 XP)\nПолучить 5 по алгебре\n(0/2," +
                " 3 дня, 10XP)");


        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoFragment fragment = new InfoFragment();
                fragment.show(getActivity().getSupportFragmentManager(), InfoFragment.TAG);
            }
        });
    }
}