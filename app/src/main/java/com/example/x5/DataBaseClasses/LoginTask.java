package com.example.x5.DataBaseClasses;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;

public class LoginTask extends AsyncTask<String, Void, Boolean> {
    Connection connection;
    String TAG = "LoginTask";

    public LoginTask(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Boolean doInBackground(String... data) {
        String login = data[0];
        int password = data[1].hashCode();
        boolean result = false;
        String query = "SELECT password FROM users WHERE login = '" + login + "'";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query); // выполняем запрос

            while (resultSet.next()) {
                int db_password = resultSet.getInt("password"); // получаем пароль из resultSet
                result = db_password == password;
            }
            resultSet.close();
            Log.i(TAG, "Task succeed");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Task failed");
        }
        return result;
    }
}
