package com.example.x5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.x5.DataBaseClasses.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.Functions;

public class MainActivity extends AppCompatActivity {

    Functions f = new Functions();
    DataBaseHelper db = new DataBaseHelper(); // создание экземпляра бд

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db.connect();

        Button login_btn = findViewById(R.id.login_button);
        Button register_button_l = findViewById(R.id.register_button_l);
        EditText login_editText_l = findViewById(R.id.login_editText_l);
        EditText password_editText_l = findViewById(R.id.password_editText_l);

        register_button_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (f.is_not_filled(password_editText_l) || f.is_not_filled(login_editText_l)) {
                    Toast.makeText(getApplicationContext(), "Какое-то из полей не заполнено!",
                            Toast.LENGTH_SHORT).show(); // проверка полей
                }
                else if (f.eight_length(password_editText_l)) {
                    Toast.makeText(getApplicationContext(), "В пароле меньше 8 символов!",
                            Toast.LENGTH_SHORT).show(); // проверка длины пароля
                }
                else {
                    String login = login_editText_l.getText().toString();
                    if (db.account_exists(login)) {
                        Toast.makeText(getApplicationContext(), "Такой аккаунт уже существует!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        String password = password_editText_l.getText().toString();
                        db.register(login, password); // вызываем функцию для регистрации
                        Intent Intent = new Intent(MainActivity.this, AppActivity.class);
                        Intent.putExtra("login_key", login);
                        MainActivity.this.startActivity(Intent); //переход в app Activity
                        db.disconnect();
                    }
                }
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f.is_not_filled(password_editText_l) || f.is_not_filled(login_editText_l)) {
                    Toast.makeText(getApplicationContext(), "Какое-то из полей не заполнено!",
                            Toast.LENGTH_SHORT).show();  // процверка полей
                } else {
                    String login = login_editText_l.getText().toString();
                    String password = password_editText_l.getText().toString();
                    if (db.login(login, password)) {  // проверка пароля
                        Intent Intent = new Intent(MainActivity.this, AppActivity.class);
                        Intent.putExtra("login_key", login);
                        MainActivity.this.startActivity(Intent); //переход в другую активность
                        db.disconnect();
                    } else {
                        Toast.makeText(getApplicationContext(), "Неверный логин или пароль!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}