package com.example.finals.Frag1ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.finals.R;

import java.util.ArrayList;


public class ListviewAdapter extends ArrayAdapter<ListData> {

    Context context;
    TextView name, age;


    public ListviewAdapter(@NonNull Context context, int resource, ArrayList<ListData> ld) {
        super(context, resource, ld);
        this.context= context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
        }
        ListData ld = getItem(position);
        name = convertView.findViewById(R.id.name);
        age = convertView.findViewById(R.id.age);

        name.setText(ld.getName());
        age.setText(String.valueOf(ld.getAge()));

        return convertView;
    }
}
