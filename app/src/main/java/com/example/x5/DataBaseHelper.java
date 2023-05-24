package com.example.x5;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBaseHelper {
    private Connection connection;

    public String user_login;
    public String Username = "Никнейм";
    public String vk_id;

    private boolean success;
    private boolean acc_exists;

    private static final String TAG = "DBHelper"; // тег для LogCat

    // парметры сервера и бд (в качестве сервера я пока использую локальный)
    private final String host = "balarama.db.elephantsql.com"; // 10.0.2.2 / arjuna.db.elephantsql.com / balarama.db.elephantsql.com
    private final String database = "alnjrvin"; // StudyFor5_DB / axjtwpko / alnjrvin
    private final int port = 5432;
    private final String user = "alnjrvin"; // postgres , axjtwpko, / alnjrvin
    private final String pass = "hIU8zyNQynyyyaBrZYy4sZ_l3IetE4EB"; // i3J!PqPz / jmrDkPhQZ2mAtofxsrgCPku47IM5xVxs / hIU8zyNQynyyyaBrZYy4sZ_l3IetE4EB

    private String url = "jdbc:postgresql://%s:%d/%s"; // строка-шаблон, для создания ссылки

    public DataBaseHelper() {  // конструктор
        this.url = String.format(this.url, this.host, this.port, this.database); // формируем полную ссылку
    }

    public void setUser_login(String login) {
        this.user_login = login;
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
                    connect();
                    String query = "INSERT INTO users (login, password) VALUES ('"  + login
                            + "', " + password + ")"; // SQL запрос в бд
                    connection.createStatement().executeUpdate(query);
                    setUser_login(login);
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запуск потока
        // отчет о потоке
        try {
            thread.join();
            Log.i(TAG, "Reg Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "Reg Thread failed!");
            e.printStackTrace();
        }
    }

    public boolean check_login(String login, int password) {// функция для проверки пароля
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    connect();
                    String query = "SELECT password FROM users WHERE login = '" + login + "'"; // SQL запрос в бд
                    ResultSet resultSet = connection.createStatement().executeQuery(query); // выполняем запрос

                    while (resultSet.next()) {
                        int rs_password = resultSet.getInt("password"); // получаем пароль из resultSet
                        success = rs_password == password; // сравниваем пароли и присваем резултат success
                        if (success) {
                            setUser_login(login);
                        }
                    }
                    Log.i(TAG, "Login checked");
                    resultSet.close();
                    connection.close();

                } catch (Exception e) {
                    Log.e(TAG, "Login check failed");
                    e.printStackTrace();
                    success = false;
                }
            }
        });

        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Check_login Thread Succeed!");
            return success;
        } catch (Exception e) {
            Log.e(TAG, "Check_login Thread failed!");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String[]> getProductList() {
        ArrayList<String[]> product_list = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connect();
                    String query = "SELECT * FROM products";
                    ResultSet resultSet = connection.createStatement().executeQuery(query);

                    while (resultSet.next()) {
                        String product_name = resultSet.getString("product_name");
                        String product_price = ""+resultSet.getInt("product_price");
                        String product_info = resultSet.getString("product_info");
                        String[] list = new String[] {product_name, product_price, product_info};
                        product_list.add(list);
                    }
                    resultSet.close();
                    connection.close();
                    Log.i(TAG, "Got product list!");
                } catch (Exception e) {
                    Log.e(TAG, "Did not get product list!");
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            thread.join();
            Log.i(TAG, "GetProductList Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "GetProductList  Thread failed!");
            e.printStackTrace();
        }
        return product_list;
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
                    connect();
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

    public boolean account_exists(String login) {
        Thread thread = new Thread(new Runnable() { // создаем экземпляр
            @Override
            public void run() { // переопределяем метод
                try {
                    connect();

                    String query = "SELECT * from users WHERE login = '" + login + "'"; // SQL запрос в бд
                    ResultSet resultSet = connection.createStatement().executeQuery(query); // выполняем запрос
                    while (resultSet.next()) {
                        acc_exists = resultSet.getString("login") != null;
                    }
                resultSet.close();
                    Log.i(TAG, "Acc checked!");
                } catch (Exception e) {
                    Log.e(TAG, "Could not check acc!");
                    acc_exists = false;
                    e.printStackTrace();
                }
            }
        });

        thread.start(); // запуск потока

        try { // отчет о потоке
            thread.join();
            Log.i(TAG, "Acc_exists Thread Succeed!");
        } catch (Exception e) {
            Log.e(TAG, "Acc_exists Thread failed!");
            e.printStackTrace();
            acc_exists = false;
        }
        return acc_exists;
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
}