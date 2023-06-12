package com.example.x5.DataBaseClasses;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetTask extends AsyncTask<Void, Void, ArrayList<String>> {
    Connection connection;
    String TAG = "GetTask";

    public GetTask(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        ArrayList<String> output = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE type = 'task'";
        try {
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                String text = rs.getString("text");
                String goal = rs.getString("goal");
                String xp = rs.getString("xp");
                String points = rs.getString("points");
                String type = rs.getString("type");
                String time = rs.getString("time");
                output.add(text);
                output.add(goal);
                output.add(xp);
                output.add(points);
                output.add(type);
                output.add(time);
            }
            rs.close();
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
