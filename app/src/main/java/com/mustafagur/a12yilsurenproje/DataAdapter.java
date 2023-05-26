package com.mustafagur.a12yilsurenproje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends BaseAdapter {

    ArrayList<DataClass> list;
    Context context;

    public DataAdapter(ArrayList<DataClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.design_itemlist,parent,false);
        }
        DataClass veri = list.get(i);
        TextView kadi = view.findViewById(R.id.design_kadi),
                telno = view.findViewById(R.id.desgin_telno);
        ImageView a = view.findViewById(R.id.design_image);
        a.setImageResource(R.drawable.pp);
        kadi.setText(veri.getKuadi());
        telno.setText(veri.getTelno());
        return view;
    }
}
