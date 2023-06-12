package com.example.x5.DataBaseClasses;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;

public class ExistTask extends AsyncTask<String, Void, Boolean> {
    Connection connection;
    String TAG = "ExistTask";

    public ExistTask(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Boolean doInBackground(String... data) {
        String login = data[0];
        String query = "SELECT * from users WHERE login = '" + login + "'"; // SQL запрос в бд
        boolean result = false;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query); // выполняем запрос
            while (resultSet.next()) {
                result = resultSet.getString("login") != null;
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
