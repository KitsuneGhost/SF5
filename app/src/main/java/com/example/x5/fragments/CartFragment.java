package com.example.x5.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.x5.DataBaseHelper;
import com.example.x5.R;
import com.example.x5.Functions;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    public void onViewCreated (View view,  Bundle savedInstanceState) {
        DataBaseHelper db = new DataBaseHelper();
        Functions f = new Functions();

        ListView product_list = getView().findViewById(R.id.product_list);

        ArrayList<String[]> products = db.getProductList(); // получаем данные из бд
        String[] names = f.get_names(products); // формируем списки с данными для заполнения
        String[] prices = f.get_prices(products);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.list_item,
                R.id.product_name_tv, names) {
            @Override // создание своего адаптера для заполнения 2-х textView
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

        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() { // нажатие на продукт
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BottomDialog bottomDialog = new BottomDialog();
                bottomDialog.set_position(position); // пердаем позицию нажатого предмета
                bottomDialog.show(getActivity().getSupportFragmentManager(), BottomDialog.TAG); // показываем фрагмент
            }
        });
    }
}