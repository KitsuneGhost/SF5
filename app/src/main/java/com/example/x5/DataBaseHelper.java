package com.example.x5;

import android.annotation.SuppressLint;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHelper {
    private Connection connection;

    private static final String TAG = "DBHelper"; // тег для LogCat

    // парметры
    private final String host = "10.0.2.2";
    private final String database = "StudyFor5_DB";
    private final int port = 5432;
    private final String user = "postgres";
    private final String pass = "i3J!PqPz";

    private String url = "jdbc:postgresql://%s:%d/%s"; // строка-шаблон, для создания ссылки

    public DataBaseHelper() {  // конструктор
        this.url = String.format(this.url, this.host, this.port, this.database); // формируем полную ссылку
        connect();
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    Log.i(TAG, "Connection succeed!");
                }
                catch (Exception e)
                {
                    Log.e(TAG, "Connection Failed!");
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
            Log.i(TAG, "Thread Succeed!");
        }
        catch (Exception e) {
            Log.e(TAG, "Thread Failed!");
            e.printStackTrace();
        }
    }


    public void register(String login, int hashcoded_password) {
        try
        {
            String query = "INSERT INTO accounts (login, hashcodedPassword) VALUES (%s, %d)";
            query = String.format(query, login, hashcoded_password);
            connection.createStatement().executeQuery(query);
            Log.i(TAG, "Query S!");
        }
        catch (SQLException e) {
            Log.e(TAG, "SQL Exception caused by register method");
            e.printStackTrace();
        }
    }

}
