package com.example.x5.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.x5.DataBaseClasses.DataBaseHelper;
import com.example.x5.R;

import java.util.ArrayList;


public class QuestsFragment extends Fragment {

    DataBaseHelper db = new DataBaseHelper();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quests, container, false);
    }

    public void onViewCreated (View view, Bundle savedInstanceState) {
        Button info_btn = view.findViewById(R.id.info_btn);
        TextView challenges = view.findViewById(R.id.challenges);
        TextView tasks = view.findViewById(R.id.tasks_tv);

        db.connect();

        ArrayList<String> task_data = db.getTask();
        ArrayList<String> challenge_data = db.getChallenge();

        String task_text = task_data.get(0);
        String task_goal = task_data.get(1);
        String task_xp = task_data.get(2);
        String task_points = task_data.get(3);
        String task_time = task_data.get(5);

        String challenge_text = challenge_data.get(0);
        String challenge_goal =challenge_data.get(1);
        String challenge_xp = challenge_data.get(2);
        String challenge_points = challenge_data.get(3);
        String challenge_time = challenge_data.get(5);

        String task = task_text + "\n"
                + "Кол-во:" + task_goal + "\n"
                + "Награда:" + task_xp + " XP и " + task_points + "баллов" + "\n"
                + "Время выполнения: " + task_time + "Дней";

        String challenge = challenge_text + "\n"
                + "Кол-во:" + challenge_goal + "\n"
                + "Награда:" + challenge_xp + " XP и " + challenge_points + "баллов" + "\n"
                + "Время выполнения: " + challenge_time + "Дней";

        tasks.setText(task);
        challenges.setText(challenge);

        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoFragment fragment = new InfoFragment();
                fragment.show(getActivity().getSupportFragmentManager(), InfoFragment.TAG);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        db.disconnect();
    }
}