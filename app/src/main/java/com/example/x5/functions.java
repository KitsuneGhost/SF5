package com.example.x5;

import android.widget.EditText;

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

}
