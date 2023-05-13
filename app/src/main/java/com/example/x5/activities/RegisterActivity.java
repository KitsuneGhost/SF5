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

public class RegisterActivity extends AppCompatActivity {

    functions f = new functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register_button_r = findViewById(R.id.register_button_r);
        EditText login_editText_r = findViewById(R.id.login_editText_r);
        EditText password_editText_r = findViewById(R.id.password_editText_r);

        register_button_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (f.is_not_filled(password_editText_r) || f.is_not_filled(login_editText_r))
                {
                    Toast.makeText(getApplicationContext(), "Какое-то из полей не заполнено!",
                            Toast.LENGTH_SHORT).show();
                }
                else if (f.eight_length(password_editText_r))
                {
                    Toast.makeText(getApplicationContext(), "В пароле меньше символов!",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DataBaseHelper DBHelper = new DataBaseHelper();
                    String login = login_editText_r.getText().toString();
                    int hashcoded_password = password_editText_r.getText().toString().hashCode();
                    DBHelper.register(login, hashcoded_password);

                    Intent Intent = new Intent(RegisterActivity.this, AppActivity.class);
                    RegisterActivity.this.startActivity(Intent); //переход в app Activity
                }
            }
        });
    }
}