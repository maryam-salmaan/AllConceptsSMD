package com.example.finals.Frag1ListView;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.finals.R;

public class Frag1 extends ListFragment {

    onItemClicked parent;


    public interface  onItemClicked {
        void onItemClick(int pos);
    }

    public Frag1() {
        // Required empty public constructor
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        parent.onItemClick(position);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        parent = (onItemClicked) context;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyApplication app = (MyApplication) requireActivity().getApplication();
        setListAdapter(new ListviewAdapter(requireContext(), R.layout.item_listview, app.listdata));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag1, container, false);
    }
}