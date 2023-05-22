package com.example.x5.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.x5.R;


public class QuestsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quests, container, false);
    }

    public void onViewCreated (View view, Bundle savedInstanceState) {
        Button info_btn = view.findViewById(R.id.info_btn);
        TextView combos = view.findViewById(R.id.combos_tv);
        TextView tasks = view.findViewById(R.id.tasks_tv);

        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoFragment fragment = new InfoFragment();
                fragment.show(getActivity().getSupportFragmentManager(), InfoFragment.TAG);
            }
        });
    }
}