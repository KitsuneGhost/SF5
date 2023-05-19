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

    // получаем списоек имен из ArrayList c данными о продукте
    public String[] get_names(ArrayList<String[]> arrayList) {
        String[] names = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            names[i] = arrayList.get(i)[0];
        }
        return names;
    }

    // аналогично методу get_names, но с ценами
    public String[] get_prices(ArrayList<String[]> arrayList) {
        String[] prices = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            prices[i] = arrayList.get(i)[1] + "$";
        }
        return prices;
    }

    // аналогично методу get_names, но с инфой по продуктам
    public String[] get_info(ArrayList<String[]> arrayList) {
        String[] infos = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            infos[i] = arrayList.get(i)[2];
        }
        return infos;
    }

    // проверка никнейма
    public boolean username_is_empty (String username) {
        return username.equals("");
    }

    // проверка никнейма №2
    public boolean usernameIsNotFilled (String username) {
        return username.equals("Введите новый ник");
    }
}
