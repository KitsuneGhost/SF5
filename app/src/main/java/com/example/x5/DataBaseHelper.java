package com.example.x5;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseHelper {
    private Connection connection;

    private static final String TAG = "DBHelper"; // тег для LogCat

    // парметры сервера и бд (в качестве сервера я пока использую локальный)
    private final String host = "10.0.2.2"; // localhost и мой IP не работают
    private final String database = "StudyFor5_DB"; // имя бд
    private final int port = 5432; // порт сервера
    private final String user = "postgres"; // пользователь
    private final String pass = "i3J!PqPz"; // пароль

    private String url = "jdbc:postgresql://%s:%d/%s"; // строка-шаблон, для создания ссылки

    public DataBaseHelper() {  // конструктор
        this.url = String.format(this.url, this.host, this.port, this.database); // формируем полную ссылку
        connect(); // вызов метода connect(), он описан ниже
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() { //создаём экземпляр класса thread
            @Override
            public void run() {  // переопределяем run()
                try {
                    Class.forName("org.postgresql.Driver"); // регистрируем драйвер
                    connection = DriverManager.getConnection(url, user, pass); // создаем подключение к бд
                    Log.i(TAG, "Connection succeed!");
                }
                catch (Exception e) { // ловим исключения
                    Log.e(TAG, "Connection Failed!");
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запускаем поток
        try {
            thread.join(); // ждем смерти потока
            Log.i(TAG, "Connection Thread Succeed!"); // сообщаем об успешном завершении
        }
        catch (Exception e) {
            Log.e(TAG, "Connection Thread Failed!"); // или о неуспешном завершении
            e.printStackTrace();
        }
    }


    public void register(String login, int password) {  // метод для регистрации пользователей
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    String query = "INSERT INTO users (id, login, password) VALUES (1, '"  + login
                            + "', " + password + ")"; // SQL запрос в бд
                    connection.createStatement().executeQuery(query); // выполняем запрос
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Reg Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "Reg Thread failed!");
            e.printStackTrace();
        }
    }
}