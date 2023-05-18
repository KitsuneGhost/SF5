package com.example.x5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.x5.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.functions;

public class MainActivity extends AppCompatActivity {

    functions f = new functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHelper db = new DataBaseHelper(); // создание экземпляра бд

        Button login_btn = findViewById(R.id.login_button);
        Button register_button_l = findViewById(R.id.register_button_l);
        EditText login_editText_l = findViewById(R.id.login_editText_l);
        EditText password_editText_l = findViewById(R.id.password_editText_l);

        register_button_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(Intent); //переход в другую активность
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
                    int password = password_editText_l.getText().toString().hashCode();
                    if (db.check_login(login, password)) {  // проверка пароля
                        Intent Intent = new Intent(MainActivity.this, AppActivity.class);
                        Intent.putExtra("login_key", login);
                        MainActivity.this.startActivity(Intent); //переход в другую активность
                    } else {
                        Toast.makeText(getApplicationContext(), "Неверный логин или пароль!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}