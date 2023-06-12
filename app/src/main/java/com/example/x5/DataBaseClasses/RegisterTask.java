package com.example.x5.DataBaseClasses;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;


public class RegisterTask extends AsyncTask<String, Void, Void> {
    String TAG = "RegisterTask";
    Connection connection;

    public RegisterTask(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Void doInBackground(String... data) {
        try {
            String login = data[0];
            int password = data[1].hashCode();
            String query = "INSERT INTO users (login, password) VALUES ('"  + login
                    + "', " + password + ")"; // SQL запрос в бд
            connection.createStatement().executeUpdate(query);
            Log.i(TAG, "Task succeed");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Task failed");
        }
        return null;
    }
}
