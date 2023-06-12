package com.example.x5.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.x5.DataBaseClasses.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.Functions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BottomDialog extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialog";
    int position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.bottom_fragment, container, false);
    }

    public void set_position(int position) {
        this.position = position;
    }

    @Override
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataBaseHelper db = new DataBaseHelper();
        Functions f = new Functions();

        db.connect();

        TextView info_view = getView().findViewById(R.id.Information_textView);
        String[] infos = f.get_info(db.getProductList());
        String info = "  " + infos[position];

        info_view.setText(info);
    }
}