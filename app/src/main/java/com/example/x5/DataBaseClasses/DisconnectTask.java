package com.example.x5.DataBaseClasses;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;

public class DisconnectTask extends AsyncTask<Connection, Void, Void> {
    String TAG = "DisconnectTask";
    
    @Override
    protected Void doInBackground(Connection... connections) {
        Connection connection = connections[0];
        try {
            connection.close();
            Log.i(TAG, "Task Succeed");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG,"Task failed");
        }
        return null;
    }
}
