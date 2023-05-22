package com.example.x5.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.x5.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class InfoFragment extends BottomSheetDialogFragment {
    public static final String TAG = "InfoDialog";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_fragment, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.Information_textView);
        textView.setText("Комбо - сложные задания, \n" +
                "награда за которые зависит от\n" +
                "количества дней, во время \n" +
                "которых ученик выполнял комбо. \n" +
                "Новое комбо появляется\n" +
                "после завершения предыдущего.\n" +
                "Комбо прерывается после \n" +
                "неудачи в его выполнении. \n" +
                "Комбо также завершится после \n" +
                "7 дней его выполнения. \n" +
                "Например, комбо\n" +
                "“поддерживайте ср. балл 5.00 по\n" +
                "физике завершится в двух\n" +
                "случаях: \n" +
                " 1. Средний балл по физике \n" +
                "     окажется ниже 5.00\n" +
                "Ученик удерживал балл 5.00 в\n" +
                "     течение всей недели ");
    }
}
