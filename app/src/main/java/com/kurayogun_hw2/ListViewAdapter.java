package com.kurayogun_hw2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kurayogun on 10/11/15.
 */
public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Country> countryList;

    public ListViewAdapter(Activity activity, List<Country> kisiler) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        countryList = kisiler;
    }

    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Object getItem(int position) {
        return countryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        view = mInflater.inflate(R.layout.listview_child, null);
        TextView textView =
                (TextView) view.findViewById(R.id.country_name);

        Country kisi = countryList.get(position);

        textView.setText(kisi.getCountry_name());

        return view;
    }

}
