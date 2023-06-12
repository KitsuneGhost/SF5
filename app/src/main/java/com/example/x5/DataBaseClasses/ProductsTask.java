package com.example.x5.DataBaseClasses;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductsTask extends AsyncTask<Void, Void, ArrayList<String[]>> {
    Connection connection;
    String TAG = "ProductsTask";

    public ProductsTask(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected ArrayList<String[]> doInBackground(Void... voids) {
        ArrayList<String[]> data = new ArrayList<>();
        try {
            String query = "SELECT * FROM products";
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            while (resultSet.next()) {
                String product_name = resultSet.getString("product_name");
                String product_price = resultSet.getString("product_price");
                String product_info = resultSet.getString("product_info");
                String[] p_data = new String[] {product_name, product_price, product_info};
                data.add(p_data);
            }
            resultSet.close();
            Log.i(TAG, "Task succeed");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Task failed");
        }
        return data;
    }
}
