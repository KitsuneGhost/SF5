package com.example.x5.DataBaseClasses;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTask extends AsyncTask<String, Void, Connection> {
    Connection connection = null;
    String TAG = "ConnectTask";

    @Override
    protected Connection doInBackground(String... data) {
        String url = data[0];
        String user = data[1];
        String pass = data[2];

        try {
            Class.forName("org.postgresql.Driver"); // регистрируем драйвер
            connection = DriverManager.getConnection(url, user, pass); // создаем подключение к бд
            Log.i(TAG, "Connection succeed!");
        }
        catch (Exception e) { // ловим исключения
            Log.e(TAG, "Connection Failed!");
            e.printStackTrace();
        }
        return connection;
    }
}
