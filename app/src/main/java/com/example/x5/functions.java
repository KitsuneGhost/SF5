package com.example.x5;

import android.widget.EditText;

import java.util.ArrayList;

public class functions {
    // проверка на пустые и незаполненные поля
    public Boolean is_not_filled(EditText et) {
        return et.getText().toString().equals("") || et.getText().toString().equals("Пароль")
                || et.getText().toString().equals("Логин");
    }

    // проверка длины строки
    public Boolean eight_length(EditText et) {
        return et.getText().toString().length() < 8;
    }

    public String[] get_names(ArrayList<String[]> arrayList) {
        String[] names = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            names[i] = arrayList.get(i)[0];
        }
        return names;
    }

    public String[] get_prices(ArrayList<String[]> arrayList) {
        String[] prices = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            prices[i] = arrayList.get(i)[1];
        }
        return prices;
    }
}
