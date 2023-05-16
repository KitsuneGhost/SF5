package com.example.x5.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.x5.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.functions;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated (View view,  Bundle savedInstanceState) {
        DataBaseHelper db = new DataBaseHelper();
        functions f = new functions();

        ListView product_list = getView().findViewById(R.id.product_list);

        ArrayList<String[]> products = db.getProductList();
        String[] names = f.get_names(products);
        String[] prices = f.get_prices(products);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.list_item,
                R.id.product_name_tv, names) {
            @Override // перезапись адаптера
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView name = view.findViewById(R.id.product_name_tv); // получаем поля
                TextView price = view.findViewById(R.id.product_price_tv);

                name.setText(names[position]); // ставим нужный текст
                price.setText(prices[position]);
                return view; // возвращаем
            }
        };

        product_list.setAdapter(adapter); // применяем адаптер
    }
}