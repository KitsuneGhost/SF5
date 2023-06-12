package com.example.x5.DataBaseClasses;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProfileTask extends AsyncTask<String, Void, ArrayList<String>> {
    Connection connection;
    String TAG = "ProfileTask";

    public ProfileTask(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected ArrayList<String> doInBackground(String... data) {
        ArrayList<String> output = new ArrayList<>();
        String login = data[0];
        String query = "SELECT * from users WHERE login = '" + login + "'";

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query); // выполняем запрос
            while (resultSet.next()) {
                output.add(resultSet.getString("username"));
                output.add("" + resultSet.getInt("level"));
                output.add("" + resultSet.getInt("xp"));
            }
            resultSet.close();
            Log.i(TAG, "Task succeed");
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Task failed");
            return null;
        }
    }
}
