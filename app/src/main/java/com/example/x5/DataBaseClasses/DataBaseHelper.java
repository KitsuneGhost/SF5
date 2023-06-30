package com.example.x5.DataBaseClasses;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBaseHelper {
    private Connection connection;

    public String user_login;
    public String Username = "Никнейм";
    public String vk_id;

    private static final String TAG = "DBHelper"; // тег для LogCat

    // парметры сервера и бд
    private final String host = "balarama.db.elephantsql.com"; // 10.0.2.2 / balarama.db.elephantsql.com
    private final String database = "alnjrvin"; // StudyFor5_DB / alnjrvin
    private final int port = 5432;
    private final String user = "alnjrvin"; // postgres / alnjrvin
    private final String pass = "hIU8zyNQynyyyaBrZYy4sZ_l3IetE4EB"; // i3J!PqPz / hIU8zyNQynyyyaBrZYy4sZ_l3IetE4EB

    private String url = "jdbc:postgresql://%s:%d/%s"; // строка-шаблон, для создания ссылки

    public DataBaseHelper() {
        // конструктор
        // формирует полную ссылку для подключения к бд
        this.url = String.format(this.url, this.host, this.port, this.database); // формируем полную ссылку
    }

    public void setUser_login(String login) {
        this.user_login = login;
    }

    public  void disconnect() {
        // метод для отключения от бд
        try {
            DisconnectTask disconnectTask = new DisconnectTask();
            disconnectTask.execute(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        // метод для подключения к бд
        ConnectTask connectTask = new ConnectTask();
        String[] data = new String[] {url, user, pass};
        connectTask.execute(data);
        try {
            connection = connectTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(String login, String password) {
        // метод для регистрации пользователей с указанными логином и паролеи
        // логин и пароль передаются в параметрах
        RegisterTask registerTask = new RegisterTask(connection);
        String[] data = new String[] {login, password};
        registerTask.execute(data);
    }

    public boolean login(String login, String password) {
        // функция для проверки пароля для указанного логина
        // логин и проверяемый пароль передаются в параметрах
        LoginTask loginTask = new LoginTask(connection);
        String[] data = new String[] {login, password};
        loginTask.execute(data);
        try {
            return loginTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean account_exists(String login) {
        // метод для проверки существования аакаунта с указанным логином
        // логин передается в качестве параметра
        ExistTask existTask = new ExistTask(connection);
        existTask.execute(login);
        try {
            return existTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<String[]> getProductList() {
        // метод для получения списка продуктов из бд
        ProductsTask productsTask = new ProductsTask(connection);
        productsTask.execute();
        try {
            return productsTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String get_username () {
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    connect();
                    String query = "SELECT * FROM users WHERE login = '" + user_login + "'"; // SQL запрос в бд
                    ResultSet resultSet = connection.createStatement().executeQuery(query); // выполняем запрос

                    while (resultSet.next()) {
                        Username = resultSet.getString("username");
                    }
                    resultSet.close();
                    connection.close();
                    Log.i(TAG, "Got Username");

                } catch (Exception e) {
                    Log.e(TAG, "Did not get Username!");
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Get_Username Thread Succeed!");
            return this.Username;
        } catch (Exception e) {
            Log.e(TAG, "Get_username Thread failed!");
            e.printStackTrace();
            return this.Username;
        }
    }


    public void update_username(String username) {
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    String query = "UPDATE users SET username = '" + username + "' WHERE login = '"
                            + user_login + "'"; // SQL запрос в бд
                    connection.createStatement().executeUpdate(query); // выполняем запрос
                    Log.i(TAG, "Username updated!");
                    connection.close();
                } catch (Exception e) {
                    Log.e(TAG, "Could not update username!");
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Update_Username Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "Update_Username Thread failed!");
            e.printStackTrace();
        }
    }


    public void delete_account() {
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    connect();
                    String query = "DELETE FROM users WHERE login = '" + user_login + "'"; // SQL запрос в бд
                    connection.createStatement().executeUpdate(query); // выполняем запрос
                    Log.i(TAG, "Account_deleted!");
                    connection.close();
                } catch (Exception e) {
                    Log.e(TAG, "Could not delete account!");
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "delete Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "delete Thread failed!");
            e.printStackTrace();
        }
    }

    public ArrayList<String> profile_fragment_setup () {
        // для получения данных о пльзователе с указанным логином
        // логин в этом методе - поле класса
        ArrayList<String> data = new ArrayList<>();
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    connect();

                    String query = "SELECT * from users WHERE login = '" + user_login + "'"; // SQL запрос в бд
                    ResultSet resultSet = connection.createStatement().executeQuery(query); // выполняем запрос
                    while (resultSet.next()) {
                        data.add(resultSet.getString("username"));
                        data.add("" + resultSet.getInt("level"));
                        data.add("" + resultSet.getInt("xp"));
                    }
                    resultSet.close();
                    Log.i(TAG, "Got data!");
                } catch (Exception e) {
                    Log.e(TAG, "Could not get data!");
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Profile Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "Profile Thread failed!");
            e.printStackTrace();
            return null;
        }
        return data;
    }

    public void vk_id_is_not_null() {
        if (vk_id == null) {
            vk_id = "Отсутсвует";
        }
    }

    public void update_VKid(String VK_id) {
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    connect();
                    String query = "UPDATE users SET vk_id = '" + VK_id + "' WHERE login = '"
                            + user_login + "'"; // SQL запрос в бд
                    connection.createStatement().executeUpdate(query); // выполняем запрос
                    Log.i(TAG, "VK_id updated!");
                    connection.close();
                } catch (Exception e) {
                    Log.e(TAG, "Could not update VK_id!");
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Update_VKid Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "Update_VKid Thread failed!");
            e.printStackTrace();
        }
    }

    public String  get_VKid() {
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    connect();
                    String query = "SELECT vk_id FROM users WHERE login = '"
                            + user_login + "'"; // SQL запрос в бд
                    ResultSet resultSet = connection.createStatement().executeQuery(query); // выполняем запрос
                    while (resultSet.next()) {
                        vk_id = resultSet.getString("vk_id");
                    }
                    vk_id_is_not_null();
                    Log.i(TAG, "Got VK_id!");
                    resultSet.close();
                    connection.close();
                } catch (Exception e) {
                    Log.e(TAG, "Could not get VK_id!");
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Get_VKid Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "Get_VKid Thread failed!");
            e.printStackTrace();
        }
        return vk_id;
    }

    public ArrayList<String> getTask() {
        ArrayList<String> output = new ArrayList<>();
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    String query = "SELECT * FROM tasks WHERE type = 'Задание'";
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
                } catch (Exception e) {
                    Log.e(TAG, "Could not get Tasks!");
                    e.printStackTrace();
                }
            }
        });
        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Get_Tasks Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "Get_Tasks Thread failed!");
            e.printStackTrace();
        }
        return output;
    }

    public ArrayList<String> getChallenge() {
        ArrayList<String> output = new ArrayList<>();
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    String query = "SELECT * FROM tasks WHERE type = 'Челлендж'";
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
                } catch (Exception e) {
                    Log.e(TAG, "Could not get challenge!");
                    e.printStackTrace();
                }
            }
        });
        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Get_challenge Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "Get_challenge Thread failed!");
            e.printStackTrace();
        }
        return output;
    }
}